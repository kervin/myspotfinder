package com.kervinramen.myspotfinder.model;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamSource;

import com.kervinramen.myspotfinder.helpers.HttpHelper;

@XmlRootElement
public class Spots {

    public ArrayList<Spot> spot;

    public ArrayList<Spot> getSpots() {
        return this.spot;
    }

    public void setSpots(ArrayList<Spot> value) {
        this.spot = value;
    }

    public Spots() {
        this.setSpots(new ArrayList<Spot>());
    }
    
    public static Spots getSpotsFromWS() {
        
        String xml = HttpHelper.getStringResponse("http://myspotfinder.appspot.com/find");
        return getSpotsFromXml(xml);
    }

    private static Spots getSpotsFromXml(String xml) {
        Spots spots = null;
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Spots.class);
            Unmarshaller um = context.createUnmarshaller();
            spots = (Spots) um.unmarshal(new StreamSource(new StringReader(xml)));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return spots;
    }

}
