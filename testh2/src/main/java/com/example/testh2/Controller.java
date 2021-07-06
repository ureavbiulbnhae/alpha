package com.example.testh2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.testh2.model.Address;
import com.example.testh2.model.AddressWithNoID;
import com.example.testh2.model.Credentials;
import com.example.testh2.model.School;
import com.example.testh2.model.SchoolWithNoID;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;


@RestController
public class Controller{
		
	//access control information
	private boolean loggedIn = false;
	private String user = "";
	private HashMap<String, String> credentials = new HashMap<String, String>();	
	
	
	//tabs name
	private String titleParam = "info";
	private String infoText = "welcome!";
		
	//names of sub pages
	private String page_accessControlLogin = "access-control.jsp";
	
	private String page_guestAccess = "guest.jsp";
	
	private String page_loggedIn = "logged-in.jsp";
	private String page_overview = "overview.jsp";
	private String page_create = "create.jsp";
	private String page_delete = "delete.jsp";
	private String page_update = "update.jsp";
	
	private String page_accessDenied = "access-denied.jsp";
	
	@Autowired
	Repository repo;

	private String pathSchoolData = "src/main/json/data.json";
	private boolean firstTime = true;
		
	//home page
	@GetMapping("/")
	public ModelAndView home() {
		if(firstTime) {
			loadSchoolData();
			loadCredentials();
			firstTime = false;
		}
		
		ModelAndView mv = new ModelAndView(page_accessControlLogin);
		mv.addObject("info", infoText);
		return mv;
	}

	//access control
	@PostMapping("/login-check")
	public ModelAndView loginCheck(String text, String password) {
		ModelAndView mv;
		//user enters correct username
		if(credentials.containsKey(text)) {
			//user enters correct password
			if(credentials.get(text).equals(password.hashCode()+"")) {
				mv = new ModelAndView(page_loggedIn);
				infoText = "logged in as "+text;
				mv.addObject(titleParam, infoText);
				accessControlChange(true, text);
				return mv;
			}else {//user enters incorrect password
				mv = new ModelAndView(page_accessDenied);
				infoText = "try again ;)";
				mv.addObject(titleParam, infoText);
				accessControlChange(false, "");
				return mv;
			}
		}else {//user enters incorrect credentials
			mv = new ModelAndView(page_accessDenied);
			infoText = "try again ;)";
			mv.addObject(titleParam, infoText);
			accessControlChange(false, "");
			return mv;
		}
	}
	
	//update access control information
	private void accessControlChange(boolean loggedIn, String user) {
		this.loggedIn = loggedIn;
		this.user = user;
	}	
	
	//log out
	@GetMapping("/log-out")
	public ModelAndView logOut() {
		ModelAndView mv = new ModelAndView(page_accessControlLogin);
		accessControlChange(false, "");
		infoText = "logged out";
		mv.addObject(titleParam, infoText);
		return mv;
	}		
	
	//load credentials from .json
	private void loadCredentials() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			InputStream inputStream = new FileInputStream(new File("src/main/json/credentials.json"));
			TypeReference<List<Credentials>> typeReference = new TypeReference<List<Credentials>>() {};
			List<Credentials> cred = mapper.readValue(inputStream, typeReference);
			for(Credentials c : cred) {
				credentials.put(c.getUsername(), c.getPassword());
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (JsonParseException e) {
			System.out.println(e);
		} catch (JsonMappingException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	//load schools data from .json
	private void loadSchoolData() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			InputStream inputStream = new FileInputStream(new File(pathSchoolData));
			TypeReference<Map<String, School>> typeReference = new TypeReference<Map<String, School>>() {};
			Map<String, School> schools = mapper.readValue(inputStream, typeReference);
		
			for(School school : schools.values()) {
				repo.save(school);
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (JsonParseException e) {
			System.out.println(e);
		} catch (JsonMappingException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
		
	
	//access denied - when someone tries to access certain page without login
	private ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView(page_accessDenied);
		infoText = "access denied";
		mv.addObject(titleParam, infoText);
		return mv;
	}
	
		
	//guest access - read only
	@GetMapping("/guest")
	public ModelAndView guest() {
		ModelAndView mv = new ModelAndView(page_guestAccess);
		accessControlChange(false, "");
		infoText = "logged in as guest";
		mv.addObject(titleParam, infoText);
		return mv;
	}
	

	//web app overview page after successful login
	@GetMapping("/overview")
	public ModelAndView overview() {
		ModelAndView mv;
		//accessing /overview logged in
		if(loggedIn) {
			mv = new ModelAndView(page_overview);
			infoText = "overview - " +user;
			mv.addObject(titleParam, infoText);
			return mv;
		}else {//accessing /overview without log in
			return accessDenied();
		}
	}
	
	
	//returns all schools
	@GetMapping("/schools")
	public List<School> schools() {
		return repo.findAll();
	}
	

	//returns specific school
	@GetMapping("/school/{id}")
	public Optional<School> school(@PathVariable("id") int id) {
		return Optional.of(repo.findById(id).orElse(new School()));
	}
		
	
	//create new school
	@GetMapping("/create")
	public ModelAndView addSchool() {
		ModelAndView mv;
		//accessing /create logged in
		if(loggedIn && user.equals("admin")) {
			mv = new ModelAndView(page_create);
			infoText = "add school";
			mv.addObject(titleParam, infoText);
			return mv;
		}else {//accessing /create without log in or with inadequate role
			return accessDenied();
		}
	}

	//finish creating new school - add into database
	@PostMapping("/finish-creating")
	public ModelAndView finishCreating(String name, String street, String village, String zip, String district, String region, String footprint) {
		ModelAndView mv;
		//accessing /create logged in
		if(loggedIn && user.equals("admin")) {
			mv = new ModelAndView(page_create);
			infoText = "added: " +name;
			mv.addObject(titleParam, infoText);
			
			School school = new School(0, name, new Address(0, street, village, zip), district, region, footprint);			
			repo.save(school);

			rewriteJsonSchoolData();
			
			return mv;
		}else {//accessing /create without log in or with inadequate role
			return accessDenied();
		}
	}
	
	//delete record by id
	@GetMapping("/delete")
	public ModelAndView deltete(String id) {
		ModelAndView mv;
		//accessing /delete logged in
		if(loggedIn && user.equals("admin")) {
			mv = new ModelAndView(page_delete);
			if(id != null) {
				int iid = Integer.parseInt(id);
				if(iid != 0) {
					infoText = "deleted: " +repo.getById(iid).getName();
					repo.deleteById(iid);
					rewriteJsonSchoolData();
				}else
					infoText = "delete record";
			}else
				infoText = "delete record";
			mv.addObject(titleParam, infoText);
			return mv;
		}else {//accessing /delete without log in or with inadequate role
			return accessDenied();
		}
	}

	
	//update school record
	@GetMapping("/update")
	public ModelAndView update() {
		ModelAndView mv;
		//accessing /update logged in
		if(loggedIn) {
			mv = new ModelAndView(page_update);
			infoText = "update record";
			mv.addObject(titleParam, infoText);			
			return mv;
		}else {//accessing /overview without log in
			return accessDenied();
		}
	}
	
	//finish updating - change record in h2
	@PostMapping("/finish-updating")
	public ModelAndView finishUpdate(int id, String name, String street, String village, String zip, String district, String region, String footprint) {
		ModelAndView mv;
		//accessing /update logged in
		if(loggedIn) {
			mv = new ModelAndView(page_update);
			infoText = "update records";
			
			//building & saving new record
			try {
				School original = repo.getById(id);
				if(!name.equals(null) && !name.equals(""))
					original.setName(name);
				if(!street.equals(null) && !street.equals(""))
					original.setAddress(new Address(original.getAddress().getId(), street, original.getAddress().getVillage(), original.getAddress().getZip()));
				if(!village.equals(null) && !village.equals(""))
					original.setAddress(new Address(original.getAddress().getId(), original.getAddress().getStreet(), village, original.getAddress().getZip()));
				if(!zip.equals(null) && !zip.equals(""))
					original.setAddress(new Address(original.getAddress().getId(), original.getAddress().getStreet(), original.getAddress().getVillage(), zip));
				if(!district.equals(null) && !district.equals(""))
					original.setDistrict(district);
				if(!region.equals(null) && !region.equals(""))
					original.setRegion(region);
				if(!footprint.equals(null) && !footprint.equals(""))
					original.setFootprint(footprint);
					
				repo.save(original);
			
				rewriteJsonSchoolData();
				
				infoText = "updated: "+repo.getById(original.getId()).getName();
			}catch (Exception e) {
				infoText = "error updating";
			}
			
			mv.addObject(titleParam, infoText);
			return mv;
		}else {//accessing /overview without log in
			return accessDenied();
		}
	}
	
	public void rewriteJsonSchoolData() {
		ObjectMapper mapper = new ObjectMapper();
		
		List<School> schools = repo.findAll();
		int i = 1;
		HashMap<String, SchoolWithNoID> json = new HashMap<String, SchoolWithNoID>();
		for(School school : schools) {
			json.put(i+"", new SchoolWithNoID(
					school.getName(), 
					new AddressWithNoID(
							school.getAddress().getStreet(), 
							school.getAddress().getVillage(), 
							school.getAddress().getZip()
					),
					school.getDistrict(),
					school.getRegion(),
					school.getFootprint()
					));
			i++;
		}
		
		
		try {
			mapper.writeValue(new File("src/main/json/data.json"), json);
		} catch (JsonGenerationException e) {
			System.out.println(e);
		} catch (JsonMappingException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}	
}