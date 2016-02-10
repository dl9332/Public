import javax.swing.JFrame;

public class RType extends JFrame 
{
	private static final long serialVersionUID = 1L;

	public RType() 
    {
        add(new Board());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("R - Type");
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) 
    {
        new RType();
    }
}
