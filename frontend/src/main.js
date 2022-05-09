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

const tableRoot = document.querySelector("#csvRoot");
const csvFileInput = document.querySelector("#csvFileInput");
const tableCsv = new TableCsv(tableRoot);

window.onload = () => {
    fetch('http://localhost:8080/games')
        .then(response => response.json())
        .then(data => tableCsv.update(
            data.map(d => [d.position, d.name, d.score]),
            ["position", "name", "score"]
        ))
}

// fetch("")
//     .then(function (response) {
//         return response.json()
//     })
//     .then()

// csvFileInput.addEventListener("change", (e) => {
//
//
//   var hello = csvFileInput.files[0]
//
//
//   Papa.parse(hello, {
//     delimiter: ",",
//     skipEmptyLines: true,
//     complete: (results) => {
//       tableCsv.update(results.data.slice(1), results.data[0]);
//     }
//   });
// });
