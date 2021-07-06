package com.example.testh2.model;
	
public class SchoolWithNoID {
		
		private String name; 
		private AddressWithNoID address;
		private String district;
		private String region;
		private String footprint;
		
		
		public SchoolWithNoID(String name, AddressWithNoID address, 
						String district, String region, String footprint) {
			this.name = name;
			this.address = address;
			this.district = district;
			this.region = region;
			this.footprint = footprint;
		}

		public SchoolWithNoID() {

		}
		
		
		@Override
		public String toString() {
			return "School [name=" + name +	", address=" + address 
					+ ", district="	+ district + ", region=" + region + ", footprint=" + footprint + "]";
		}
		
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public AddressWithNoID getAddress() {
			return address;
		}

		public void setAddress(AddressWithNoID address) {
			this.address = address;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		public String getFootprint() {
			return footprint;
		}

		public void setFootprint(String foorprint) {
			this.footprint = foorprint;
		}	
}
