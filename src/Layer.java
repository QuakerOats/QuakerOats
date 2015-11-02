import java.util.ArrayList;
import java.util.List;


public abstract class Layer<T> {

	private List<T> layer;
	
	public Layer() {
		
	}

	public List<T> getLayer() {
		return layer;
	}

	public void setLayer(List<T> layer) {
		this.layer = layer;
	}

}
