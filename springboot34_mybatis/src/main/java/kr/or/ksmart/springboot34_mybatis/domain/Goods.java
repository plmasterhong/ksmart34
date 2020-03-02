package kr.or.ksmart.springboot34_mybatis.domain;

public class Goods {
	private String g_code;
	private String g_size;
	private String g_color;
	private String g_price;
	private String m_id;
	public String getG_code() {
		return g_code;
	}
	public void setG_code(String g_code) {
		this.g_code = g_code;
	}
	public String getG_size() {
		return g_size;
	}
	public void setG_size(String g_size) {
		this.g_size = g_size;
	}
	public String getG_color() {
		return g_color;
	}
	public void setG_color(String g_color) {
		this.g_color = g_color;
	}
	public String getG_price() {
		return g_price;
	}
	public void setG_price(String g_price) {
		this.g_price = g_price;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Goods [g_code=");
		builder.append(g_code);
		builder.append(", g_size=");
		builder.append(g_size);
		builder.append(", g_color=");
		builder.append(g_color);
		builder.append(", g_price=");
		builder.append(g_price);
		builder.append(", m_id=");
		builder.append(m_id);
		builder.append("]");
		return builder.toString();
	}
	
	
}
