package com.kervinramen.myspotfinder.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import android.os.Parcel;
import android.os.Parcelable;


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
public class Spot implements Parcelable{

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
    
    public Spot(Parcel source){
        /*
         * Reconstruct from the Parcel
         */

        this.spotId = source.readLong();
        this.name = source.readString();
        this.location = source.readString();
        this.description = source.readString();
        this.image = source.readString();
  }

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		arg0.writeLong(spotId);
		arg0.writeString(name);
		arg0.writeString(location);
		arg0.writeString(description);
		arg0.writeString(image);

	}
	
	   public static final Parcelable.Creator<Spot> CREATOR
       = new Parcelable.Creator<Spot>() {
   public Spot createFromParcel(Parcel in) {
       return new Spot(in);
   }

   public Spot[] newArray(int size) {
       return new Spot[size];
   }
};

    
}
