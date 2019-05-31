import axios from '../untils/http'
import QS from 'qs'
import base from './base'

export function deployContract(data) {
    return axios({
        url: `${base.url}/MainContract/deploy`,
        method: 'post',
        data: QS.stringify(data)
    })
}

export function getMainContractAddress(data) {
    return axios({
        url: `${base.url}/MainContract/MainContractAddress`,
        method: 'get',
        data: QS.stringify(data)
    })
}

export function saySomething(data) {
    return axios({
        url: `${base.url}/speak/something?content=${data['content']}&vol=${data['vol']}&speed=${data['speed']}&pit=${data['pit']}&per=${data['per']}`,
        method: 'get',
        data: QS.stringify(data)
    })
}