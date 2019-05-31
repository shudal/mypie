import axios from '../untils/http'
import QS from 'qs'
import base from './base'

export function setPercents(data) {
    return axios({
        url: `${base.url}/MainContract/percents`,
        method: 'post',
        data: QS.stringify(data)
    })
}

export function getMeta() {
    return axios({
        url: `${base.url}/MainContract/meta`,
        method: 'get'
    })
}
