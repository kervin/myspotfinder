package com.kervinramen.myspotfinder.model;

import java.util.ArrayList;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.kervinramen.myspotfinder.helpers.HttpHelper;

@Root(name = "spots")
public class Spots {
    @ElementList(inline = true)
    private ArrayList<Spot> spot;

    public ArrayList<Spot> getSpots() {
        return this.spot;
    }

    public void setSpots(ArrayList<Spot> value) {
        this.spot = value;
    }

    public Spots() {
        this.setSpots(new ArrayList<Spot>());
    }

    public static Spots getSpotsFromWS(String username, Double lat, Double lng) {

        String xml = HttpHelper.getStringResponse("http://myspotfinder.appspot.com/find" + "?id=" + username + "&lat="
                + lat.toString() + "&lng=" + lng.toString());
        return getSpotsFromXml(xml);
    }

    private static Spots getSpotsFromXml(String xml) {
        Spots spots = null;

        try {

            Serializer serializer = new Persister();
            spots = serializer.read(Spots.class, xml, false);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return spots;
    }

}
