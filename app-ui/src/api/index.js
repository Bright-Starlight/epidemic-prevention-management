import axios from 'axios'



import {BrowserRouter} from 'react-router-dom'
import {message} from "antd";
const router = new BrowserRouter()

const API = axios.create({

})

API.interceptors.response.use(response => {
    const msg  = response.data.message; //这里的response数据结构不一样，直接打印出来看，参照后端返回的结果
    if(msg === "没有登录"){
        //当token超时or失效 403账号无权限的时候直接跳转到/login页重新登录
        router.history.push('/')
        window.location.reload()
    }
    return response
})

export { API }