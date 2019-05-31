import axios from '../untils/http'
import QS from 'qs'
import base from './base'

export function shutdown() {
    return axios({
    url: `${base.url}/system/shutdown`,
    method: 'post',
    })
}

export function reboot() {
    return axios({
        url: `${base.url}/system/reboot`,
        method: 'post',
    })
}