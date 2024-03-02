package SeleniumFramework.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		//FileUtils is available in commons.io dependancy
		//json to string
		//readfiletostring is deprecated hence we need to pass value of encoding format we are trying to write the string
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") +"\\src\\test\\java\\SeleniumFramework\\data\\PurchaseOrder.json"), StandardCharsets.UTF_8);
		//jackson databind for string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List <HashMap<String,String>> map = mapper.readValue(jsonContent, new TypeReference <List<HashMap<String,String>>>(){});
		return map;
	}
}
