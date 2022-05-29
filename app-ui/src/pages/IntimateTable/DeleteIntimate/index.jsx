import React, { useState } from 'react';
import { Modal, Button,message } from 'antd';

import {API} from "../../../api";

function DeleteIntimate (props) {
    const refresh = props.refresh
    const [isModalVisible, setIsModalVisible] = useState(false);
    const {id} = props
    const showModal = () => {
        setIsModalVisible(true);
    };

    const handleOk = () => {
        deleteData()
        setIsModalVisible(false);
    };
    const deleteData = () => {
        console.log(id)
        API.get("http://localhost:3000/carrier/delete",{
            params:{
                id:id
            }}).then(res=>{
            if (res.data.flag === true){
                message.success(res.data.message)
                refresh()
            }else {
                message.error(res.data.message)
            }
        })
    };

    const handleCancel = () => {
        setIsModalVisible(false);
    };

    return (
        <>
            <Button type="primary" onClick={showModal} danger>
                删除
            </Button>
            <Modal title="警告！！！" visible={isModalVisible} onOk={handleOk} onCancel={handleCancel}>
               是否删除
            </Modal>
        </>
    );
};

export default DeleteIntimate;