package com.mmtax.common.utils.esign.helper;

import com.alibaba.fastjson.JSONObject;
import com.mmtax.common.exception.BusinessException;
import com.mmtax.common.utils.esign.comm.CacheKeyConstant;
import com.mmtax.common.utils.esign.comm.ConfigConstant;
import com.mmtax.common.utils.esign.comm.HttpHelper;
import com.mmtax.common.utils.esign.comm.RequestHelper;
import com.mmtax.common.utils.esign.domain.request.CommonRequest;
import com.mmtax.common.utils.esign.enums.RequestType;
import com.mmtax.common.utils.redis.RedisLoaderUtils;
import com.mmtax.common.utils.redis.RedisUtil;

import java.util.concurrent.TimeUnit;

/**
 * @description 鉴权认证 辅助类
 * @author 宫清
 * @date 2019年7月19日 上午11:54:28
 * @since JDK1.7
 */
public class TokenHelper {

	/**
	 * 不允许外部创建实例
	 */
	private TokenHelper() {
	}

	// -------------------------------------------公有方法start-------------------------------------------------------

	/**
	 * @description 获取token
	 * 
	 *              实际使用中，可获取token后，放在redis缓存中，如果是非分布式架构，也可使用guava的LoadingCache或者自己构建本地缓存来存储该token，
	 *              缓存存储时，需要注意缓存失效截止时间expiresIn
	 * 
	 * @date 2019年7月19日 上午11:55:57
	 * @author 宫清
	 * @throws BusinessException
	 */
	public static JSONObject getTokenData(String appId,String appSecret) throws BusinessException {
		JSONObject json = HttpHelper.doCommHttp(RequestType.GET,
				ConfigConstant.getTokenUrl(appId, appSecret), null);
		CommonRequest commonRequest = RequestHelper.castDataJson(json);

		// 存放入redis缓存
		toLocalCache(commonRequest.getData());

		return json;

	}

	/**
	 * @description 刷新token
	 * 
	 *              刷新token后，新旧token共存时间为5分钟，实际使用中，若使用redis或者guava的LoadingCache或自建本地缓存的方式来存储token，请注意刷新缓存时间
	 * 		 刷新token和重新获取token效果一样			
	 * 
	 * @param refreshToken {@link String} 之前获取token时返回的refreshToken
	 * @date 2019年7月19日 上午11:55:57
	 * @author 宫清
	 * @throws BusinessException
	 */
	public static JSONObject refreshToken(String refreshToken,String appId) throws BusinessException {
		JSONObject json = HttpHelper.doCommHttp(RequestType.GET,
				ConfigConstant.refreshTokenUrl(appId, refreshToken), null);
		json = RequestHelper.castDataJson(json).getData();

		// 存放入redis缓存
		toLocalCache(json);

		return json;
	}
	
	// -------------------------------------------公有方法end---------------------------------------------------------

	// -------------------------------------------私有方法start-------------------------------------------------------

	/**
	 * @description 存放入redis缓存
	 *
	 * @param json
	 * @author 宫清
	 * @date 2019年7月20日 下午4:04:08
	 */
	private static void toLocalCache(JSONObject json) {
		RedisUtil.put(CacheKeyConstant.TOKEN, json.getString("token"),30, TimeUnit.MINUTES);
		RedisUtil.put(CacheKeyConstant.REFRESH_TOKEN, json.getString("refreshToken"),30, TimeUnit.MINUTES);
//		LocalCacheHelper.put(CacheKeyConstant.TOKEN, json.getString("token"));
//		LocalCacheHelper.put(CacheKeyConstant.REFRESH_TOKEN, json.getString("refreshToken"));
	}

	// -------------------------------------------私有方法end---------------------------------------------------------
}
