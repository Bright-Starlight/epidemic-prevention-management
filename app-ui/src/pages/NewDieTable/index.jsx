import {Button, Input, message, Space, Table} from 'antd';
import Highlighter from 'react-highlight-words';
import {SearchOutlined} from '@ant-design/icons';
import React from 'react';
import CreateDie from './CreateDie/index'
import UpdateDie from "./UpdateDie/index";

import {API} from "../../api";
import DeleteDie from "./DeleteDie/index";




class NewDieTable extends React.Component {


   componentDidMount() {
       this.page = 1
       this.pageSize = 5
       API.get("http://localhost:3000/carrier/getNewDie",{params:{
           page:1,
               pageSize:5
           }}).then(
           res => {
               if (res.data.flag === true){
                   let data = res.data.data.list
                   this.setState({data:[...data],total:res.data.data.total})
               }else {
                   message.error(res.data.message)
               }

           }
       )
   }


    state = {data:[],
        searchText: '',
        searchedColumn: '',
    };
    page = 0;
    pageSize = 0;

   onChange = (page,pageSize)=>{
       this.page = page
       this.pageSize = pageSize
       API.get("http://localhost:3000/carrier/getNewDie",{params:{
               page:page,
               pageSize:pageSize
           }}).then(
           res => {
               if (res.data.flag === true){
                   let data = res.data.data.list
                   this.setState({data:[...data],total:res.data.data.total})
               }else {
                   message.error(res.data.message)
               }

           }
       )
   }

    getColumnSearchProps = dataIndex => ({
        filterDropdown: ({setSelectedKeys, selectedKeys, confirm, clearFilters}) => (
            <div style={{padding: 8}}>
                <Input
                    ref={node => {
                        this.searchInput = node;
                    }}
                    placeholder={`搜索 ${dataIndex}`}
                    value={selectedKeys[0]}
                    onChange={e => setSelectedKeys(e.target.value ? [e.target.value] : [])}
                    onPressEnter={() => this.handleSearch(selectedKeys, confirm, dataIndex)}
                    style={{marginBottom: 8, display: 'block'}}
                />
                <Space>
                    <Button
                        type="primary"
                        onClick={() => this.handleSearch(selectedKeys, confirm, dataIndex)}
                        icon={<SearchOutlined/>}
                        size="small"
                        style={{width: 90}}
                    >
                        搜索
                    </Button>
                    <Button onClick={() => this.handleReset(clearFilters)} size="small" style={{width: 90}}>
                        清空
                    </Button>
                    <Button
                        type="link"
                        size="small"
                        onClick={() => {
                            confirm({closeDropdown: false});
                            this.setState({
                                searchText: selectedKeys[0],
                                searchedColumn: dataIndex,
                            });
                        }}
                    >
                        Filter
                    </Button>
                </Space>
            </div>
        ),
        filterIcon: filtered => <SearchOutlined style={{color: filtered ? '#1890ff' : undefined}}/>,
        onFilter: (value, record) =>
            record[dataIndex]
                ? record[dataIndex].toString().toLowerCase().includes(value.toLowerCase())
                : '',
        onFilterDropdownVisibleChange: visible => {
            if (visible) {
                setTimeout(() => this.searchInput.select(), 100);
            }
        },
        render: text =>
            this.state.searchedColumn === dataIndex ? (
                <Highlighter
                    highlightStyle={{backgroundColor: '#ffc069', padding: 0}}
                    searchWords={[this.state.searchText]}
                    autoEscape
                    textToHighlight={text ? text.toString() : ''}
                />
            ) : (
                text
            ),
    });

    handleSearch = (selectedKeys, confirm, dataIndex) => {
        confirm();
        this.setState({
            searchText: selectedKeys[0],
            searchedColumn: dataIndex,
        });
    };

    handleReset = clearFilters => {
        clearFilters();
        this.setState({searchText: ''});
    };


    render() {

        const columns = [
            {
                title: '姓名',
                dataIndex: 'name',
                key: 'name',

                ...this.getColumnSearchProps('name'),
            },
            {
                title: '身份证号码',
                dataIndex: 'identityCard',
                key: 'identityCard',

                ...this.getColumnSearchProps('identityCard'),
            },
            {
                title: '年龄',
                dataIndex: 'age',
                key: 'age',
                ...this.getColumnSearchProps('age'),
            },
            {
                title: '性别',
                dataIndex: 'gender',
                key: 'gender',
                ...this.getColumnSearchProps('gender'),

            },
            {
                title: '家庭地址',
                dataIndex: 'homeAddress',
                key: 'homeAddress',
                ...this.getColumnSearchProps('homeAddress'),

            },
            {
                title: '联系方式',
                dataIndex: 'telephoneNumber',
                key: 'telephoneNumber',
                ...this.getColumnSearchProps('telephoneNumber'),

            },
            {
                title: '接触原因',
                dataIndex: 'cause',
                key: 'cause',
                ...this.getColumnSearchProps('cause'),

            },
            {
                title: '所在医院',
                dataIndex: 'fromHospital',
                key: 'fromHospital',
                ...this.getColumnSearchProps('fromHospital'),

            },
            {
                title: '隔离时间',
                dataIndex: 'isolationTime',
                key: 'isolationTime',
                ...this.getColumnSearchProps('isolationTime'),

            },
            {
                title: 'Action',
                dataIndex: '',
                key: 'x',
                render: (text) => <div>
                    <UpdateDie data={text} refresh={()=>{this.onChange(this.page,this.pageSize)}}/>
                    <br/>
                    <DeleteDie  id={text.id} refresh={()=>{this.onChange(this.page,this.pageSize)}}/>

                </div>,
            },
        ];
        return <div>
            <h3><b>新增遇难详情</b></h3>
            <div style={{float:"right"}}><CreateDie refresh={()=>{this.onChange(this.page,this.pageSize)}}/></div>
            <Table columns={columns}
                   size="small"
                   pagination={{defaultPageSize:5,
                       current:this.state.current,
                       onChange:this.onChange,total:this.state.total
                   }}
                   dataSource={this.state.data}/>
        </div>;
    }
}

export default NewDieTable;