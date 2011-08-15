package com.kervinramen.myspotfinder.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


/**
 * This class stores the places that will have ratings on Spotfinder. 
 * Each place will be an instance of this class
 * 
 * This may evolve into a dynamic class in the future where the places 
 * are fetched from a web service.
 * 
 * @author Kervin Ramen
 * 
 */
@Root(name="spot")
public class Spot {

    @SuppressWarnings("unused")
    private String encodedKey;

    
    /**
     * The long part of the key
     */
    @Element(required=false)
    private Long spotId;
    /** 
     * Name of the spot
     */
    @Element(required=false)
	private String name;

	/**
	 * Gmaps location
	 */
    @Element(required=false)
	private String location;

	/**
	 * Some brief description
	 */
    @Element(required=false)
	private String description;
	
	/** 
	 * Image of the spot, 
	 * place in /media/
	 */
    @Element(required=false)
	private String image;

    
    public Long getSpotId() {
        return this.spotId;
    }
    
    public void setSpotId(Long value) {
        this.spotId = value;
    }

    public String getName() {
		return this.name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String value) {
		this.location = value;
	}

	public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public Spot() {
    }
    
}
