package fr.neatmonster.nocheatplus.config;

import java.lang.reflect.Field;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.DumperOptions;

public class RawConfigFile  extends YamlConfiguration{
	
    /**
     * Return double within given bounds, with preset. Mainly used for hidden settings.
     * 
     * @param data
     * @param path
     * @param min
     * @param max
     * @param preset
     * @return
     */
    public double getDouble(final String path, final double min, final double max, final double preset){
    	final double value = getDouble(path, preset);
    	if (value < min) return min;
    	else if (value > max) return max;
        else return value;
    }
    
    /**
     * Attempt to get a type id from the path somehow, return null if nothing found.<br>
     * Will attempt to interpret strings, will return negative or out of range values.
     * @param path
     * @return
     */
    public Integer getTypeId(final String path){
        return getTypeId(path, null);
    }
    
    /**
     * Attempt to get a type id from the path somehow, return preset if nothing found.<br>
     * Will attempt to interpret strings, will return negative or out of range values.
     * @param path
     * @param preset
     * @return
     */
    public Integer getTypeId(final String path, final Integer preset){
        String content = getString(path, null);
        if (content != null){
            Integer id = parseTypeId(content);
            if (id != null) return id;
        }
        int id = getInt(path, Integer.MAX_VALUE);
        return id == Integer.MAX_VALUE ? preset : id;
    }

    /**
     * Attempt to get an int id from a string.<br>
     * Will return out of range numbers, attempts to parse materials.
     * @param content
     * @return
     */
    public static Integer parseTypeId(String content) {
        content = content.trim().toUpperCase();
        try{
            return Integer.parseInt(content);
        }
        catch(NumberFormatException e){}
        try{
            Material mat = Material.matchMaterial(content.replace(' ', '_').replace('-', '_').replace('.', '_'));
            if (mat != null) return mat.getId();
        }
        catch (Exception e) {}
        return null;
    }
    
    /* (non-Javadoc)
     * @see org.bukkit.configuration.file.YamlConfiguration#saveToString()
     */
    @Override
    public String saveToString() {
        // Some reflection wizardly to avoid having a lot of linebreaks in the yaml file, and get a "footer" into the file.
    	// TODO: Interesting, but review this: still necessary/useful in CB-1.4 ?.
        try {
            Field op;
            op = YamlConfiguration.class.getDeclaredField("yamlOptions");
            op.setAccessible(true);
            final DumperOptions options = (DumperOptions) op.get(this);
            options.setWidth(200);
        } catch (final Exception e) {}

        return super.saveToString();
    }

}
