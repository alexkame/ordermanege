package com.thinkgem.jeesite.weixin.message.req;

/**
 * 视频消息
 * @author janson
 *
 */
public class VideoMessage extends BaseMessage{

	//视频消息媒体id
	private String MediaId;
	private String ThumbMediaId;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	
	
	
}
