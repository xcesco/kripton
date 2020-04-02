package net.sqlcipher;

public class SQLException extends RuntimeException
{
	private static final long serialVersionUID = -7121966710436839085L;

	public SQLException() {}

    public SQLException(String error)
    {
        super(error);
    }
}
