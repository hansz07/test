package githubtest;


public class StringTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(String.format("%d+%d",1,2));
		
		Person p = new Person();
		System.out.println(p.getName());
		Person p1 = new Child();
		System.out.println(p1.getName());
	}

}
