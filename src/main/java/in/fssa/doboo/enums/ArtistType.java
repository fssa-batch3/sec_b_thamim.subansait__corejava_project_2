package in.fssa.doboo.enums;

public enum ArtistType {
	musicproducer("mp"),
	beatmakers("bm"),
	mixmastereng("mme"),
	singer("sin"),
	songwriter("son"),;
	 private final String value;

	ArtistType(String string) {
		 this.value = string;
	}
	
	 public String getValue() {
	        return value;
	    }
	 
	 public static String tpye(String input) {
	        String lowerCaseInput = input.toLowerCase();
	        switch (lowerCaseInput) {
	            case "musicproducer":
	                return musicproducer.value;
	            case "beatmakers":
	                return beatmakers.value;
	            case "mixmastereng":
	                return mixmastereng.value;
	            case "singer":
	                return singer.value;
	            case "songwriter":
	                return songwriter.value;
	            default:
	                throw new IllegalArgumentException("Invalid input string for artist type: " + input);
	        }
	    }
	    
	    public static String getType(String input) {
	        String lowerCaseInput = input.toLowerCase();
	        switch (lowerCaseInput) {
	            case "mp":
	                return "musicproducer";
	            case "bm":
	                return "beatmakers";
	            case "mme":
	                return "mixmastereng";
	            case "sin":
	                return "singer";
	            case "son":
	                return "songwriter";
	            default:
	                throw new IllegalArgumentException("Invalid input string for UsedDuration: " + input);
	        }
	    }

}
