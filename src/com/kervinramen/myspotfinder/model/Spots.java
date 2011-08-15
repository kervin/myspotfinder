package com.kervinramen.myspotfinder.model;

import java.io.StringReader;
import java.util.ArrayList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;





import com.kervinramen.myspotfinder.helpers.HttpHelper;

@Root(name="spots")
public class Spots {
	@ElementList(inline=true)
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
    
    public static Spots getSpotsFromWS() {
        
        String xml = HttpHelper.getStringResponse("http://myspotfinder.appspot.com/find");
        return getSpotsFromXml(xml);
    }

    private static Spots getSpotsFromXml(String xml) {
        Spots spots = null;
       /* JAXBContext context;*/
        try {
//            context = JAXBContext.newInstance(Spots.class);
//            Unmarshaller um = context.createUnmarshaller();
//            spots = (Spots) um.unmarshal(new StreamSource(new StringReader(xml)));
            
            Serializer serializer = new Persister();
           /* File source = new File("example.xml");*/

            spots = serializer.read(Spots.class, xml, false);
            

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return spots;
    }

}
