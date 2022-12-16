
/**
 * Description: This is the profile class which stores
 * the data of a profile. This class utilizes getter and
 * setter methods to moddify/change the data in each
 * profile. The displayProfile method also implements
 * the getBreadthFirstTraversal of the Undirected Graph
 * data structure.
 *
 * Team Name: Runtime Errors
 * @authors Aung Bo Bo, Wai Han
 * Editor: VS Code
 */

// import java.util.Iterator;

public class Profile {
	private String name;
	private String status;

	public Profile() {
		name = "";
		status = "";
	}

	public Profile(String name, String status) {
		this.name = name;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void displayProfile(QueueInterface<Profile> friendQueue, HashedDictionary<String, Profile> networkOfProfiles) {
		System.out.println("\tName    : " + name);
		System.out.println("\tStatus  : " + status);
		System.out.print("\tFriends : ");

		String friendname = "";
		while (!friendQueue.isEmpty()) {
			friendname = friendQueue.dequeue().getName();

			if (friendname == this.getName() || networkOfProfiles.getValue(friendname) == null)
				System.out.print("");
			else
				System.out.print(friendname + ", ");
		}
		System.out.println();
	}
}