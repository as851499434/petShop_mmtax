package com.mmtax.common.utils.esign.enums;

import org.apache.http.client.methods.*;

/**
 * @description 请求类型
 * @author 宫清
 * @date 2019年7月10日 下午10:18:29
 * @since JDK1.7
 */
public enum RequestType {
	//post
	POST{
		@Override
		public HttpRequestBase getHttpType(String url) {
			return new HttpPost(url);
		}
	},
	//get
	GET{
		@Override
		public HttpRequestBase getHttpType(String url) {
			return new HttpGet(url);
		}
	},
	//delete
	DELETE{
		@Override
		public HttpRequestBase getHttpType(String url) {
			return new HttpDelete(url);
		}
	},
	//put
	PUT{
		@Override
		public HttpRequestBase getHttpType(String url) {
			return new HttpPut(url);
		}
	},
	
	;
	
   public abstract HttpRequestBase getHttpType(String url);
}
