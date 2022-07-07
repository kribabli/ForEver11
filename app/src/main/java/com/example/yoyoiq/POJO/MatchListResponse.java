package com.example.yoyoiq.POJO;

import com.google.gson.annotations.SerializedName;

public class MatchListResponse{

	@SerializedName("datetime")
	private String datetime;

	@SerializedName("response")
	private Response response;

	@SerializedName("modified")
	private String modified;

	@SerializedName("etag")
	private String etag;

	@SerializedName("api_version")
	private String apiVersion;

	@SerializedName("status")
	private String status;

	public void setDatetime(String datetime){
		this.datetime = datetime;
	}

	public String getDatetime(){
		return datetime;
	}

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
	}

	public void setModified(String modified){
		this.modified = modified;
	}

	public String getModified(){
		return modified;
	}

	public void setEtag(String etag){
		this.etag = etag;
	}

	public String getEtag(){
		return etag;
	}

	public void setApiVersion(String apiVersion){
		this.apiVersion = apiVersion;
	}

	public String getApiVersion(){
		return apiVersion;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}