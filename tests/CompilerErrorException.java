package tests;

import java.util.Collection;

public class CompilerErrorException extends Exception {
	
	private Collection errors;

	public void setErrors(Collection errors) {
		this.errors = errors;
	}

	public Collection getErrors() {
		return errors;
	}

}
