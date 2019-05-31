import axios from '../untils/http'
import QS from 'qs'
import base from './base'

/**
 * post方法，对应post请求
 * @desc注册请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function userLogin(data) {
    return axios({
        url: `${base.url}/user/login`,
        method: 'post',
        data: QS.stringify(data)
    })
}

export function userRegister(data) {
    return axios({
        url: `${base.url}/user/register`,
        method: 'post',
        data: QS.stringify(data)
    })
}
/**
 * get方法，对应get请求
 * @desc登录请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function userInfo(id) {
    return axios({
        url: `${base.url}/user/profile?id=${id}`,
        method: 'get'
    })
}

export function createStatus(transactionHash) {
    return axios({
        url: `${base.url}/user/createStatus?transactionHash=${transactionHash}`,
        method: 'get'
    })
}

export function changePwd(data) {
    return axios({
        url: `${base.url}/user/password`,
        method: 'post',
        data: QS.stringify(data)
    })
}

