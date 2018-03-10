package com.acchain.community.bean;

/**
 * 店铺信息
 */
public class StoreInfo
{
	protected int Id;//店铺的id，通过这个id来区分是哪个店铺的
	protected String name;
	protected boolean isChoosed;
    private boolean isEditor;

	public boolean isEditor() {
		return isEditor;
	}

	public void setIsEditor(boolean isEditor) {
		this.isEditor = isEditor;
	}

	public StoreInfo(int id, String name) {
		Id = id;
		this.name = name;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isChoosed() {
		return isChoosed;
	}

	public void setChoosed(boolean isChoosed) {
		this.isChoosed = isChoosed;
	}
}
