import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')

class TableCsv {
  /**
   * @param {HTMLTableElement} root The table element which will display the CSV data.
   */
  constructor(root) {
    this.root = root;
  }

  /**
   * Clears existing data in the table and replaces it with new data.
   *
   * @param {string[][]} data A 2D array of data to be used as the table body
   * @param {string[]} headerColumns List of headings to be used
   */
  update(data, headerColumns = []) {
    this.clear();
    this.setHeader(headerColumns);
    this.setBody(data);
  }

  /**
   * Clears all contents of the table (incl. the header).
   */
  clear() {
    this.root.innerHTML = "";
  }

  /**
   * Sets the table header.
   *
   * @param {string[]} headerColumns List of headings to be used
   */
  setHeader(headerColumns) {
    this.root.insertAdjacentHTML(
        "afterbegin",
        `
            <thead>
                <tr>
                    ${headerColumns.map((text) => `<th>${text}</th>`).join("")}
                </tr>
            </thead>
        `
    );
  }

  /**
   * Sets the table body.
   *
   * @param {string[][]} data A 2D array of data to be used as the table body
   */
  setBody(data) {
    const rowsHtml = data.map((row) => {
      return `
                <tr>
                    ${row.map((text) => `<td>${text}</td>`).join("")}
                </tr>
            `;
    });

    this.root.insertAdjacentHTML(
        "beforeend",
        `
            <tbody>
                ${rowsHtml.join("")}
            </tbody>
        `
    );
  }
}

function square(url_l) {
  $(document).ready(function(){
    $('#load_data').click(function(){
      $.ajax({
        url:url_l,
        dataType:"text",
        success:function(data)
        {
          var employee_data = data.split(/\r?\n|\r/);
          var table_data = '<table class="table table-bordered table-striped">';
          for(var count = 0; count<employee_data.length; count++)
          {
            var cell_data = employee_data[count].split(",");
            table_data += '<tr>';
            for(var cell_count=0; cell_count<cell_data.length; cell_count++)
            {
              if(count === 0)
              {
                table_data += '<th>'+cell_data[cell_count]+'</th>';
              }
              else
              {
                table_data += '<td>'+cell_data[cell_count]+'</td>';
              }
            }
            table_data += '</tr>';
          }
          table_data += '</table>';
          $('#employee_table').html(table_data);
        }
      });
    });

  });
}

window.onload = function() {
  square("gamescore.csv");
};


