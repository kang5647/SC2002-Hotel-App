package hotelReservation;

public interface EditFile {
	
	public void readFile(String filePath);
	
	public void edit(String filePath);
	
	public void addRecord(String filePath);
	
	public void deleteRecord(String filePath);
	
}
