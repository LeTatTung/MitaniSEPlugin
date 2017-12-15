package ontology.exceptions;

public class ArraySizeException extends Exception{
	private static final long serialVersionUID = 1L;
	private String message;
	private String causedBy;
	/**
	 * Exception xay ra khi read file owl bi loi (do ko tim thay file, file ko dung cu phap...)
	 * @param message: thong bao loi
	 * @param causedBy: nguyen nhan gay ra loi
	 */
	public ArraySizeException()
	{
		this.message = "Size of input value is not equal with the number of columns";
		this.causedBy = "Input String[] in method addRow(String[]) has size not equal with number of columns";
	}
	public String getMessage() {
		return message;
	}
	public String getCausedBy() {
		return causedBy;
	}	
}
