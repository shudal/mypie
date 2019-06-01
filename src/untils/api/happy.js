import axios from '../../untils/http'
import base from '../base'

export function getVideo(videoClass, pageSize, pageNumber) {
    return axios({
        url: `${base.url}/video?class=${videoClass}&pageSize=${pageSize}&pageNumber=${pageNumber}`,
        method: 'get',
    })
}
