<template>
  <div class="container">
  <H1 align="center">SampleNews</H1>
    <!-- 表单用于添加和编辑数据 -->
    <form @submit.prevent="submitForm" class="form-container">
      <div class="form-item">
        <label for="title">标题</label>
        <input type="text" id="title" v-model="formData.title" />
      </div>
      <div class="form-item">
        <label for="content">内容</label>
        <textarea id="content" v-model="formData.content" rows="4"></textarea>
      </div>
      <div class="form-item">
        <label for="imageUrl">图像地址</label>
        <input type="text" id="imageUrl" v-model="formData.imageUrl" />
      </div>
      <div class="form-item">
        <button type="submit">提交</button>
      </div>
    </form>

    <!-- 表格显示数据 -->
    <table class="data-table">
      <tr>
        <th>标题</th>
        <th class="content-column">内容</th>
        <th class="image-url-column">图像地址</th>
        <th>动作</th>
      </tr>
      <tr v-for="item in items" :key="item.id" @click="editItem(item)">
        <td>{{ item.title }}</td>
        <td class="content-column">{{ item.content }}</td>
        <td class="image-url-column">{{ item.imageUrl }}</td>
        <td>
        <div class="action-buttons">
        <button @click="editItem(item)">编辑</button>
        <button @click="deleteItem(item.id)">删除</button>
        </div>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      items: [],
      formData: {
        title: '',
        content: '',
        imageUrl: ''
      },
      // 更多状态...
    };
  },
  methods: {
    fetchItems() {
      axios.get('http://localhost:3000/api/items')
        .then(response => {
          this.items = response.data;
        });
    },
    submitForm() {
      if (this.formData.id) {
        // 更新操作
        axios.put(`http://localhost:3000/api/items/${this.formData.id}`, this.formData)
          .then(response => {
            console.log(response.data.message);
            this.fetchItems(); // 重新获取数据以更新表格
            this.resetForm();  // 重置表单
          })
          .catch(error => {
            console.error(error);
          });
      } else {
        // 添加操作
        axios.post('http://localhost:3000/api/items', this.formData)
          .then(response => {
            console.log(response.data.message);
            this.fetchItems(); // 重新获取数据以更新表格
            this.resetForm();  // 重置表单
          })
          .catch(error => {
            console.error(error);
          });
      }
    },
    resetForm() {
      this.formData = { title: '', content: '', imageUrl: '' };
    },
    editItem(item) {
      // 填充表单以编辑数据
      this.formData = Object.assign({}, item);
    },
    deleteItem(id) {
      // 发送请求以删除数据
      axios.delete(`http://localhost:3000/api/items/${id}`)
        .then(response => {
          console.log(response.data.message);
          this.fetchItems(); // 重新获取数据以更新表格
        })
        .catch(error => {
          console.error(error);
        });
    },
  },
  mounted() {
    this.fetchItems();
  }
}
</script>

<style>
.container {
  width: 100%;
  max-width: 1200px; /* 或根据需要调整 */
  margin: 0 auto;
  padding: 20px;
}

.form-container {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
}

.form-item {
  margin-bottom: 10px;
}

.form-item label {
  display: block;
  margin-bottom: 5px;
}

.form-item input[type="text"],
.form-item textarea {
  width: 50%; /* 输入框宽度 */
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.form-item textarea {
  resize: vertical; /* 允许垂直调整大小 */
}

.data-table {
  width: 100%;
  border-collapse: collapse;
}

.data-table th,
.data-table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.data-table th {
  background-color: #f2f2f2;
}

.data-table .content-column {
    width: 60%; /* 或根据您的布局需求调整 */
  }

  .data-table .image-url-column {
    width: 20%; /* 或根据您的布局需求调整 */
  }


.data-table .action-buttons {
    display: flex;
    gap: 10px;
  }

  .data-table button {
    padding: 5px 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    cursor: pointer;
    background-color: #f5f5f5;
  }

  .data-table button:hover {
    background-color: #e2e2e2;
  }

  

</style>