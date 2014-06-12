package pl.krzaku.proz.view;

import java.util.LinkedList;
import java.util.List;

public class Layout
{
	private List<Renderable> renderList;
	private List<SoundID> soundList;
	
	public Layout()
	{
		renderList = new LinkedList<Renderable>();
		soundList = new LinkedList<SoundID>();
	}
	
	public void add(Renderable bullet)
	{
		renderList.add(bullet);
	}
	
	public void addSound(SoundID sound)
	{
		soundList.add(sound);
	}
	
	public List<Renderable> getList()
	{
		return renderList;
	}
	
	public List<SoundID> getSoundList()
	{
		return soundList;
	}
}
