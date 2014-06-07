package pl.krzaku.proz.view;

import java.util.LinkedList;
import java.util.List;

public class Layout
{
	private List<Renderable> renderList;
	
	public Layout()
	{
		renderList = new LinkedList<Renderable>();
	}
	
	public void add(Renderable bullet)
	{
		renderList.add(bullet);
	}
	
	public List<Renderable> getList()
	{
		return renderList;
	}
}
