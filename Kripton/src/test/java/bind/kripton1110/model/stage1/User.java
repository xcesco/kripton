package bind.kripton1110.model.stage1;

import java.util.List;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class User {

	public String about;

	public String address;

	public int age;

	public String balance;

	public String company;

	public String email;

	public String eyeColor;

	public String favoriteFruit;

	public List<Friend> friends;

	public String greeting;

	public String guid;

	@Bind("_id")
	public String id;

	public List<Image> images;

	public int index;

	public boolean isActive;

	public double latitude;

	public double longitude;

	public Name name;

	public String phone;

	@Bind("picture")
	public String pictureUrl;

	public List<Integer> range;

	public String registered;

	public List<String> tags;
}