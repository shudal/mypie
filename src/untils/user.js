import axios from '../untils/http'
import QS from 'qs'
import base from './base'

export function userLogin(data) {
    return axios({
        url: `${base.url}/user/login`,
        method: 'post',
        data: QS.stringify(data)
    })
}

