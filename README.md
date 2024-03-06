# ADV Shop

> #### Winoto Hasyim - 2206025243 - Pemrograman Lanjut B

## Module 4 - Pemrograman Lanjut 2023/2024 Genap

1. **Is this TDD flow useful enough for you or not. If not, explain things that you need to do next time you make more tests.**

TDD sangat berguna karena memiliki berbagai manfaat:
- Code Qualitynya jadi lebih baik karena dengan fondasi test yang dibuat di awal development, kita bisa jadi terstruktur dalam mengimplementasi kodenya
- Debugging juga menjadi lebih mudah karena Test sudah dipredefined
- TDD dapat membuat kita berfokus pada suatu hal terlebih dahulu, seperti membuat test kemudian mendevelop programnya sehingga kita tidak harus memikirkan potential bug ketika memprogram

2. **Reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.**

Menurut saya, test saya sudah memenuhi FIRST Principle karena saya usdah mengisolasi test-testnya dan tidak membuat test menjadi terlalu panjang, serta testnya memiliki assertion di dalamnya. Selain itu, testnya mengkover hampir seluruh line of code. Hal ini sesuai dengan FIRST Principle Yaitu, Fast, Independent, Repeatable, Self-Validating dan timely/thorough.


<details>
<summary>Module 3</summary>

## Module 3 - Pemrograman Lanjut 2023/2024 Genap

1. **Explain what principles you apply to your project!**
    Di dalam proyek saya, saya menggunakan SOLID principle:
    
    <br>**Single Responsibility Principle (SRP):**
    Setiap class pada direktori `model`, `repository`, `service`, dan `controller` memiliki hanya satu tujuan, misalnya, class Product dan Car bertujuan untuk mendifinisikan model dari masing-masing objek tersebut, ProductRepository dan CarRepository dipakai hanya sebagai database bagi objek-objek product dan car, ProductServiceImpl dan CarServiceImpl dipakai hanya untuk menyediakan opsi modifikasi yang dapat dilakukan pada database, serta CarController dan ProductController berfungsi untuk handling HTTP request untuk masing-masing Car dan Product. Sebelumnya, CarController ini adalah sebuah subclass dari ProductController, tapi karena hal ini melanggar SRP, maka CarController dipisahkan dari ProductController. Hal ini karena Setiap Controller harus berurusan dengan object yang sesuai dengan controller tersebut. Jika CarController extend dari ProductController, maka CarController akan memiliki 2 responsibilities, yaitu untuk menghandle request yang bersesuaian dengan Product dan Car objects.

    <br>**Open-Closed Principle (OCP):**
    Setiap class pada projek ini mengimplementasi Open-Closed Principle karena jika kita ingin meng-extend behaviour dari sebuah class, class tersebut tidak perlu diubah kodenya dan kita hanya harus meng-extend dari class itu sendiri.

    <br>**Liskov Substitution Principle (LSP):**
    Sebelumnya, CarController merupakan subclass dari ProductController. Ini adalah hal yang melanggar SOLID principle terutama pada bagian LSP karena CarController tidak berfungsi seperti ProductController sehingga CarController tidak bisa dipakai untuk menggantikan ProductController. Alasan lainnya adalah karena CarController juga akan memakai 2 service yaitu dari ProductService dan CarService

    <br>**Interface Segregation Principle (ISP):**
    Sebelumnya tidak terdapat interface Repository, maka dari itu saya membuat interface tersebut untuk CarRepository dan ProductRepository. Alasannya adalah RepositoryInterface bisa dipakai sebagai sebuah utility bagi file Repository agar bisa bekerja seperti Database bagi object-object yang bersesuaian.

    <br>**Dependency Inversion Principle (DIP):**
    Seperti pada ISP, saya memakai interface untuk repository agar bisa bekerja sebagai database, yang artinya repository tersebut dependent dengan interface tersebut. Selain itu, CarController juga dependent pada interface CarService daripada class implementasinya yaitu CarServiceImpl.
    <br>

2. **Advantages of Applying SOLID Principles:**
    - ***Maintainability***: Dengan memenuhi SRP pada SOLID principle, perubahan pada satu bagian fungsionalitas akan terisolasi pada kelas yang menangani fungsionalitas tersebut, mengurangi risiko memunculkan bug di bagian lain kode. Oleh karena itu, maintenance yang harus dilakukan juga akan berkurang.
    - ***Extensibility***: Prinsip SOLID membuat kode lebih ekstensibel. Misalnya, dengan mematuhi Open-Closed Principle (OCP), fungsionalitas baru dapat ditambahkan dengan membuat kelas baru, tanpa perlu memodifikasi kelas yang ada. Hal ini menyebabkan jarangnya muncul kode yang diakibatkan oleh modifikasi source code.
    - ***Modularity***: Dengan mematuhi Dependency Inversion Principle (DIP), high-level module tidak langsung bergantung pada low-level module, sehingga memudahkan untuk mengganti implementasi yang berbeda dari lower-level module.
    - ***Readability***: Dengan mematuhi prinsip SOLID, struktur kode menjadi lebih rapi dan mudah dipahami. Hal ini sangat membantu saat ada developer lain yang perlu memahami kode dalam project.
    - ***Testability***: Kode yang dirancang dengan prinsip SOLID biasanya lebih mudah untuk diuji. Misalnya, dengan mematuhi Interface Segregation Principle (ISP), kita dapat menguji fungsionalitas spesifik yang disediakan oleh suatu interface, tanpa perlu khawatir tentang dependensi lainnya.

3. **Disasdvantages of not applying SOLID principles to your project with examples.**
    - ***Tight Coupling***: Tanpa Dependency Inversion Principle (DIP), high-level module mungkin langsung bergantung pada low-level module, menyebabkan Tight Coupling. Ini membuatnya sulit untuk mengubah satu modul tanpa memengaruhi modul lain. Misalnya, jika CarController langsung bergantung pada CarServiceImpl, setiap perubahan pada CarServiceImpl mungkin memerlukan perubahan pada CarController.
    - ***Hard to Maintain***: Tanpa Single Responsibility Principle (SRP), sebuah kelas mungkin bertanggung jawab atas beberapa bagian fungsionalitas. Ini membuat kelas sulit dipahami dan dipelihara, dan perubahan pada satu bagian fungsionalitas mungkin memunculkan bug di bagian lain. Misalnya, jika kelas Vehicle bertanggung jawab untuk mengendalikan mobil dan sepeda motor, maka perubahan pada cara mengendalikan mobil bisa mempengaruhi cara mengendalikan sepeda motor.
    - ***Hard to Extend***: Tanpa Open-Closed Principle (OCP), menambahkan fungsionalitas baru mungkin memerlukan modifikasi kelas yang ada, yang meningkatkan risiko memunculkan bug. Misalnya, jika kita ingin menambahkan jenis kendaraan baru dan kita tidak memiliki kelas dasar Vehicle, kita mungkin perlu memodifikasi kode yang menangani kendaraan untuk mendukung jenis baru.

</details>

<details>
<summary>Module 2</summary>

## Module 2 - Pemrograman Lanjut 2023/2024 Genap

### Reflection
1. Ada beberapa code quality issue yang saya perbaiki:
- **Fields in interfaces and annotations are automatically public static final, and methods are public abstract.** Issue ini terjadi di interface ProductService yang di dalamnya terdapat modifier public untuk setiap atributnya. Setiap atribut adalam interface secara implisit sudah public static final. Oleh karena itu, saya bisa memperbaiki issue tersebut dengan menghapus modifier public pada setiap atribut itu.
- **Unused import 'org.springframework.web.bind.annotation.*'.** Issue ini terdapat pada beberapa komponen pada spring project eshop ini. Hal yang saya lakukan untuk memperbaikinya adalah untuk mengimport method-method yang dipakai saja (alias tidak memakai * dalam proses meng-import method dari library)

2. Menurut saya, saya telah mengimplementasi konsep CI/CD dalam pengerjaan modul saya. Continuous Integration bisa dicapai karena terdapat Workflow yang mengerjakan test suite pada project saya sehingga code saya secara otomatis akan di-test setiap di push ke GitHub. Dalam kata lain, kode saya menerapkan automated testing dalam pengecekan kode. Selain itu, Continuous Deployment juga bisa dicapai karena saya sudah menggunakan Koyeb sebagai PaaS project ini. Setiap kali saya push atau pull request ke main branch, Koyeb akan mendeteksi perubahan kode apa saja yang terjadi. Setelah itu, Koyeb akan secara otomatis men-deploy project saya berdasarkan push atau pull request terakhir yang dilakukan
</details>