import React, { useState, useEffect } from 'react';
import ReactDOM from 'react-dom';
import { Line } from '@ant-design/plots';
import {API} from "../../../api";
import {message} from "antd";

function BrokenLine  ()  {
    const [data, setData] = useState([]);

    useEffect(() => {
        asyncFetch();
    }, []);

    const asyncFetch = () => {
        API("http://localhost:3000/report/getReport").then(res=>{
            if (res.data.flag === true){
                setData(res.data.data)
            }else {
                message.error(res.data.message)
            }
        })
    };
    const config = {
        data,
        xField: 'date',
        yField: 'value',
        seriesField: 'title',
        smooth:true,
        autoFit:true,
        yAxis: {
            label: {
                // 数值格式化为千分位
                formatter: (v) => `${v}`.replace(/\d{1,3}(?=(\d{3})+$)/g, (s) => `${s},`),
            },
        },
        color: ['#FAA219', '#bd8d86','#29c61a','#ff0000'],
    };

    return <Line {...config} />;
};
export default BrokenLine;