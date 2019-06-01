import axios from '../../untils/http'
import QS from 'qs'
import base from '../base'

export function setMeta(data) {
    return axios({
        url: `${base.url}/meta`,
        method: 'post',
        data: QS.stringify(data)
    })
}

export function getMeta() {
    return axios({
        url: `${base.url}/meta`,
        method: 'get'
    })
}

