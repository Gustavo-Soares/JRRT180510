package p;
class Inner {
	/** Comment */
	private A a;

	public void doit() {
		///this.
		a.foo(///this.
		      a.bar());
	}

	/**
	 * @param a
	 */
	Inner(A a) {
		this.a = a;
	}
}