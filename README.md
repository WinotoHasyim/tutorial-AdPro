# ADV Shop

> #### Winoto Hasyim - 2206025243 - Pemrograman Lanjut B

## Module 2 - Pemrograman Lanjut 2023/2024 Genap

### Reflection
1. Ada beberapa code quality issue yang saya perbaiki:
- **Fields in interfaces and annotations are automatically public static final, and methods are public abstract.** Issue ini terjadi di interface ProductService yang di dalamnya terdapat modifier public untuk setiap atributnya. Setiap atribut adalam interface secara implisit sudah public static final. Oleh karena itu, saya bisa memperbaiki issue tersebut dengan menghapus modifier public pada setiap atribut itu.
- **Unused import 'org.springframework.web.bind.annotation.*'.** Issue ini terdapat pada beberapa komponen pada spring project eshop ini. Hal yang saya lakukan untuk memperbaikinya adalah untuk mengimport method-method yang dipakai saja (alias tidak memakai * dalam proses meng-import method dari library)

2. Menurut saya, saya telah mengimplementasi konsep CI/CD dalam pengerjaan modul saya. Continuous Integration bisa dicapai karena terdapat Workflow yang mengerjakan test suite pada project saya sehingga code saya secara otomatis akan di-test setiap di push ke GitHub. Dalam kata lain, kode saya menerapkan automated testing dalam pengecekan kode. Selain itu, Continuous Deployment juga bisa dicapai karena saya sudah menggunakan Koyeb sebagai PaaS project ini. Setiap kali saya push atau pull request ke main branch, Koyeb akan mendeteksi perubahan kode apa saja yang terjadi. Setelah itu, Koyeb akan secara otomatis men-deploy project saya berdasarkan push atau pull request terakhir yang dilakukan