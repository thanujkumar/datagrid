package basics;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MultiMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultiMapExample {
    public static void main(String[] args) {
        HazelcastInstance hz = Hazelcast.newHazelcastInstance();
        //One important point that you should be aware of is that you can't use a Hazelcast map in the pass-by-reference context as you might in a native Java implementation
        Map<String, List<String>> manualCities = hz.getMap("manualCities");

        List<String> gbCities = new ArrayList<String>();
        manualCities.put("GB", gbCities);

        gbCities = manualCities.get("GB");
        gbCities.add("London");
        manualCities.put("GB", gbCities);

        gbCities = manualCities.get("GB");
        gbCities.add("Southampton");
        manualCities.put("GB", gbCities);

        /*
         * This is because Hazelcast always returns a cloned copy of the data rather than the instance actually held.
         * Therefore, modifying the returned object as you would in the preceding code does not actually update the persisted value.
         */
        List<String> frCities = new ArrayList<>();
        manualCities.put("FR", frCities);

        frCities = manualCities.get("FR");
        frCities.add("Paris");
        manualCities.put("FR", frCities);

        System.err.println(
                String.format("Manual: GB=%s, FR=%s",
                        manualCities.get("GB"),
                        manualCities.get("FR")));


        MultiMap<String, String> multiMapCities =
                hz.getMultiMap("multiMapCities");

        multiMapCities.put("GB", "London");
        multiMapCities.put("GB", "Southampton");

        multiMapCities.put("FR", "Paris");

        System.err.println(
                String.format("MultiMap: GB=%s, FR=%s",
                        multiMapCities.get("GB"),
                        multiMapCities.get("FR")));
    }
}