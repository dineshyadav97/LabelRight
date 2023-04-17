package test;


public class ArtworkDto {

	private int artworkID;
	private int errorCount;
	
	
	public ArtworkDto() {
		super();
	}

	public ArtworkDto(int artworkID, int errorCount) {
		super();
		this.artworkID = artworkID;
		this.errorCount = errorCount;
	}

	public int getArtworkID() {
		return artworkID;
	}
	
	public void setArtworkID(int artworkID) {
		this.artworkID = artworkID;
	}
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	
}
