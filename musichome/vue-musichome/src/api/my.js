import request from "@/utils/request"
export default {
     // 分页获取列表
     search(page, size) {
        return request({
            url: `/my/list/search/${page - 1}/${size}`,
            method: 'post',
            // data: searchMap
        })
    },
    // search( size,current) {
    //     return request({
    //         url: `/my?size=${size}&current=${current-1}`,
    //         method: 'get',
    //         // data: searchMap
    //     })
    // },
    // 上方根据曲名和作者名模糊查询
    findMys(searchMap) {
        return request({
            url: `/my/findMys`,
            method: 'post',
            data: searchMap
        })
    },

    // 查询
    getMyById(id) {
        return request({
            url: `/my/getMyById/${id}`, // 反单引号 `
            method: 'get'
        })
    },


    // 新增会员
    add(form) {
        return request({
            url: '/my/addMys',
            method: 'post',
            data: form
        })
    },

    //删除
    deleteMyById(id) {
        return request({
            url: `/my/delteMyById/${id}`, // 反单引号 ``
            method: 'delete', // delete 方式提交
        })
    },
    // 更新
    update(form) {
        return request({
            url: `/my/updateMy`, // 反单引号 ``
            method: 'put', // put 方式提交
            data: form
        })
    },

    // //上传
    // upload(){
    //     return request({
    //         url: `/my/img`, // 反单引号 ``
    //         method: 'post', // put 方式提交
            
    //     })
    // },
    //获取当前行的图片路径通过当前行的id
    getURLById(myid){
        return request({
            url: `/my/img/${myid}`, // 反单引号 ``
            method: 'get', // put 方式提交
            
        })
    },
    


}