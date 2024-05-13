package com.study.disgroupportal.data

import android.text.format.DateUtils.WEEK_IN_MILLIS
import androidx.compose.runtime.mutableStateListOf
import com.study.disgroupportal.DisGroupPortalApp.Companion.curTime
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.model.employee.UserRole.ADMIN
import com.study.disgroupportal.model.news.New
import com.study.disgroupportal.model.portal.Departament
import com.study.disgroupportal.model.portal.Departament.Development_Department
import com.study.disgroupportal.model.requests.Request
import com.study.disgroupportal.model.requests.RequestStatus.OPENED
import com.study.disgroupportal.model.requests.RequestTheme.ARRANGEMENT
import kotlin.random.Random
import kotlin.random.Random.Default.nextLong

object Presets {

    val requestsPresets = listOf(
        Request(
            theme = ARRANGEMENT,
            date = curTime - WEEK_IN_MILLIS,
            authorId = "123654",
            problem = "Здравствуйте, хотела отдать свой голос за благоустройство района. Мне понравилась идея с обустройством детской площадкой возле реки по адресу: г.Каховка, ул.Сосновка, д.8-16",
            status = OPENED,
            authorName = "Кристина Пользователевна",
            answer = ""
        )
    )

    val newsPresets = listOf(
        New(
            imageUrl = "https://api.rbsmi.ru/attachments/ebcea0cfe1b6c8ae759b3b5dbfcd4888ad1c94fc/store/crop/0/0/4500/3000/4500/3000/0/d9ca3f38bf454964df48ef7d93a3388edf1536221fe076927a6f4f0f0801/a76814765178182da9fbac731123812c.jpg",
            title = "«СберТех» и DIS Group объявили о завершении тестовых испытаний на совместимость своих флагманских продуктов",
            link = "https://dis-group.ru/dis-news/sbertech-i-dis-group-obyavili-o-zavershenii-testovih-ispitanij-na-sovmestimost-svoih-flagmanskih-produktov/",
            date = curTime - 60_000 * nextLong(20, 200),
            text = "Решения в области управления данными, бизнес-аналитики и защиты информации от «Дата Инновации» и «Юниверс Дата» (входят в продуктовый портфель DIS Group) успешно прошли тесты на совместимость с реляционной системой управления базами данных (СУБД) и операционной системой от «СберТеха».\n" +
                    "\n" +
                    "В рамках проекта была протестирована совместимость решений, разработанных компанией «Дата Инновации»: «Плюс7 ФормИТ», «Плюс7 ФормИТ» на Hadoop, «Плюс7 ФормИТ DQ» на Hadoop, «Плюс7 ФормИТ Маскинг», «Плюс7 EDM», а также продуктов разработчика «Юниверс Дата» по управлению данными «Юниверс MDM» и «Юниверс DG» с СУБД Platform V Pangolin и операционной системой Platform V SberLinux OS Server.\n" +
                    "\n" +
                    "Мастер-дистрибьютером решений «Дата Инновации» и «Юниверс Дата» на российском рынке является компания DIS Group, специалисты которой участвовали в тестировании. Результаты тестов на совместимость открывают для DIS Group возможность успешной работы с заказчиками, которые уже используют или планируют использовать решения от СберТеха.\n" +
                    "\n" +
                    "Павел Лихницкий, генеральный директор DIS Group, сказал:\n" +
                    "\n" +
                    "«DIS Group проводит работу по тестированию продуктов, проверяя их на устойчивость к нагрузкам, соответствие в части функциональных, технических и эксплуатационных характеристик, которые заказчик предъявляет к нужному ПО. Результаты тестов подтверждают, что продукты «Дата Инновации», «Юниверс Дата» и «СберТеха» готовы к совместному использованию и обеспечат необходимый уровень работоспособности и надежности».\n" +
                    "\n" +
                    "Максим Тятюшев, генеральный директор «СберТеха», отметил: «В настоящий момент во всех отраслях экономики наблюдается значительное увеличение объема данных. Эффективная работа с данными становится конкурентным преимуществом бизнеса. Современные решения, которые предлагает DIS Group, нуждаются в продуктах, которые могут обеспечить высокую доступность, производительность и безопасность. С учетом этих требований рынка мы и создавали наши продукты».\n" +
                    "\n" +
                    "Platform V Pangolin – СУБД корпоративного уровня, которая основана на хорошо зарекомендовавшей себя открытой СУБД PostgreSQL и содержит ряд важных доработок. Это целевая СУБД в «Сбере» и других крупных российских компаниях. Более 70 тыс. инсталляций Platform V Pangolin успешно эксплуатируются в составе приложений и сервисов разного уровня критичности и масштаба.\n" +
                    "\n" +
                    "Platform V SberLinux OS Server – серверная операционная система корпоративного уровня, которая позволяет строить высокопроизводительные программно-аппаратные комплексы. Решения «СберТеха» зарегистрированы в реестре российского ПО.\n" +
                    "\n" +
                    "В портфеле DIS Group присутствует весь набор инструментов по управлению данными, способный обеспечить переход с решений иностранного производства на отечественные платформы."
        ),
        New(
            imageUrl = "https://www.basistemi.com/wp-content/uploads/2012/12/Depositphotos_13383349_original.jpg",
            title = "«Дата инновации» интегрировала YandexGPT в систему управления знаниями «Плюс7 МаяК»",
            link = "https://dis-group.ru/mm-about-us/data-innovatsii-vnedrila-yandexgpt-v-sistemu-upravlenia-znaniyami-plus7-mayak/",
            date = curTime - 60_000 * nextLong(20, 200),
            text = "Компания «Дата инновации» встроила нейросеть YandexGPT в систему для централизованного управления знаниями «Плюс7 МаяК». Благодаря этому пользователи непосредственно в интерфейсе системы могут делать запросы и обрабатывать текстовую информацию с помощью генеративного искусственного интеллекта. Об этом CNews сообщили представители «Дата инновации».\n" +
                    "\n" +
                    "Система «Плюс7 МаяК» используется компаниями для повышения эффективности внешних коммуникаций и внутрикорпоративных взаимодействий. В ее основе лежит постоянно обновляемая база информации и интерфейсы интеграции с внешними CRM-сервисами, корпоративным веб-сайтом, чат-ботами и др.\n" +
                    "\n" +
                    "Интеграция «Плюс7 МаяК» и YandexGPT позволяет автоматизировать рутинные процессы обработки текстовой информации, освобождая пользователям системы время для более важных задач. Так, например, нейросеть может помочь сделать краткую выжимку главных идей из объемной статьи для формирования почтовой рассылки или для создания постов в соцсетях.\n" +
                    "\n" +
                    "Генеральный директор «Дата инновации» Олег Гиацинтов:\n" +
                    "\n" +
                    "«Мы видим большой интерес со стороны клиентов к инструментам с использованием искусственного интеллекта. Система Плюс7 МаяК с помощью YandexGPT получила доступ к мощным алгоритмам машинного обучения, которые расширили возможности пользователей и упростили работу с большими объемами информации».\n" +
                    "\n" +
                    "«Дата Инновации» – российская компания, центр разработки программного обеспечения для работы с данными и знаниями. Флагманские решения компании: «Плюс7 ФормИТ» для интеграции данных, качества данных и маскирования данных, «Плюс7 Маяк» для управления знаниями и другие решения для комплексной цифровой трансформации. Все продукты включены в реестр ПО Минцифры. Мастер-дистрибьютером решений «Дата Инновации» на российском рынке является компания DIS Group."
        ),
        New(
            imageUrl = "https://www.cea.fr/cea-tech/pns/PublishingImages/Nanomat%C3%A9riaux/CEA002308_SHUTTERSTOCKHD.jpg",
            title = "Продолжаем представлять спикеров Дата Саммита 2024!",
            link = "https://dis-group.ru/dis-news/prodolzhaem-predstavlyat-spikerov-data-sammita-2024/",
            date = curTime - 60_000 * nextLong(20, 200),
            text = "Напоминаем, мероприятие пройдет 30 мая, количество мест ограничено — самое время зарегистрироваться и отметить дату в календаре.\n" +
                    "\n" +
                    "Игорь Гончаров, руководитель службы управления данными “Уралсиб”, участник дискуссии “Проприетарное ПО и Open Source: риски и возможности”\n" +
                    "Николай Шевцов, СDO, ОТП Банк, расскажет о стремительном развитии бизнеса на основе данных\n" +
                    "Евгений Бурнаев, профессор, руководитель центра прикладного ИИ Сколтеха, поделится опытом работы с искусственным интеллектом.\n" +
                    "Среди слушателей: руководители по данным, СDO, директора по цифровой трансформации, руководители бизнес-подразделений крупнейших российских корпораций."
        ),
        New(
            imageUrl = "https://img.redzhina.ru/img/12/c2/12c24124b6a532977f3ca099f6ec2509.jpg",
            title = "Завтра, 17 апреля, состоится церемония награждения премии Data Fusion Award. Болеем за проекты DIS Group и клиентов",
            link = "https://dis-group.ru/dis-news/zavtra-sostoitsya-premia-data-fusion-award/",
            date = curTime - 60_000 * nextLong(20, 200),
            text = "В этом году на премию Data Fusion Award подали 4 проекта:\n" +
                    "\n" +
                    "Переход «Московского кредитного банка» (МКБ) на российское ETL-решение «Плюс7 формИТ». В результате проекта подразделения МКБ стали вовремя получать аналитическую и управленческую отчетности, основанные на актуальных данных.\n" +
                    "Проект по улучшению сервиса для граждан, заказчик – ДИТ Москвы. В результате проекта 27 млн. жителей Москвы стали быстрее получать достоверную информацию с помощью МDМ-Системы.\n" +
                    "Внедрение платформы управления данными Magnit Data (инструменты Data Governance & Data Quality). Инструменты встроены в действующие производственные процессы развития и сопровождения хранилищ данных и аналитической отчетности; встраиваются в процессы управления\n" +
                    "Также DIS Group участвует в номинации «Популяризация и продвижение инновационных подходов к управлению данными. Наша программа евангелизации включает в себя разработку и продвижение обучающих материалов, проведение бесплатных вебинаров, участие в форумах, написание руководств и книг, а также обучение профессионалов в авторизованном тренинг-центре DIS Group c выдачей сертификатов."
        ),
        New(
            imageUrl = "https://wudgleyd.ru/wp-content/uploads/c/6/6/c6647859c4b388d2ad94922ad4f1fe82.jpeg",
            title = "Вышла новая версия платформы для работы с данными Плюс7 ФормИТ",
            link = "https://dis-group.ru/dis-news/vishla-novaya-versia-s-dannimi-plus7-formit/",
            date = curTime - 60_000 * nextLong(20, 200),
            text = "Компания «Дата инновации» представила новую версию многофункциональной платформы для интеграции, обеспечения качества и маскированию данных Плюс7 ФормИТ 1.5.1.\n" +
                    "\n" +
                    "В версии Плюс7 ФормИТ 1.5.1 пользователям стал доступен более широкий набор совместимых систем управления баз данных. Обновленная платформа поддерживает последние версии PostgreSQL, Greenplum, Hadoop и российские сборки на их основе: Platform V Pangolin, PostgrePro, Arenadata Hadoop, Arenadata DB, RT.DataLake. Решение позволяет настроить процессы наполнения хранилищ и озер данных и обмена информацией в гетерогенной инфраструктуре заказчиков.\n" +
                    "\n" +
                    "\n" +
                    "Кросс-отраслевая платформа Плюс7 ФормИТ используется для интеграции, сбора и обмена корпоративными данными любого формата и сложности, обеспечения качества данных, их обогащения, а также защиты данных путем их маскирования. С ее помощью компании могут создать единое цифровое пространство и повысить эффективность работы с данными: консолидировать их и оперативно обмениваться ими внутри организации, с партнерами и клиентами. Решение помогает интегрировать информацию из множества корпоративных ресурсов, включая файлы, web-формы и электронную почту.\n" +
                    "\n" +
                    "Олег Гиацинтов, генеральный директор «Дата инновации»:\n" +
                    "\n" +
                    "«Платформа Плюс7 ФормИТ за короткий срок завоевала доверие наших клиентов, ее используют крупнейшие российские банки, промышленные предприятия и торговые компании для эффективной работы с корпоративными данными. С момента запуска в 2022 году решение постоянно совершенствовалось. Обновленная версия позволит расширить спектр возможностей пользователей по интеграции с базами данных на основе open source решений».\n" +
                    "\n" +
                    "О «Дата инновации»\n" +
                    "\n" +
                    "«Дата Инновации» – российская компания, центр разработки программного обеспечения для работы с данными и знаниями. Флагманские решения компании: Плюс7 ФормИТ для интеграции данных, качества данных и маскирования данных, Плюс7 Маяк для управления знаниями и другие решения для комплексной цифровой трансформации. Все продукты включены в реестр ПО Минцифры. Мастер-дистрибьютером решений «Дата Инновации» на российском рынке является компания DIS Group."
        ),
        New(
            imageUrl = "https://www.mgpu.ru/wp-content/uploads/2020/04/inf-teh.jpg",
            title = "DIS Group представила свои решения на АКПО-КОНФ 2024",
            link = "https://dis-group.ru/dis-news/dis-group-predstavila-svoi-resheniya-na-akpo-konf-2024/",
            date = curTime - 60_000 * nextLong(20, 200),
            text = "4 апреля, DIS Group представила свои решения на первой флагманской кросс-отраслевой конференции Ассоциации крупнейших потребителей программного обеспечения и оборудования АКПО-КОНФ 2024.\n" +
                    "\n" +
                    "АКПО-КОНФ – это экспертная площадка для диалога, обмена мнениями и опытом между компаниями, крупнейшими потребителями ПО и оборудования, производителями отрасли и регуляторами.\n" +
                    "Мероприятие состоится в Экспоцентре, павильон «Форум».\n" +
                    "\n" +
                    "Мы познакомили Вас с нашими инновационными решениями в области управления данными, бизнес-аналитики и защиты информации на базе ведущих технологий."
        ),
        New(
            imageUrl = "https://msk.kprf.ru/wp-content/uploads/2022/12/3e7a2533c16cf41a41c5e70be2d740ea92ca9054.jpg",
            title = "Глава DIS Group Павел Лихницкий принял участие в круглом столе Комитета СФ по рынку больших данных",
            link = "https://dis-group.ru/dis-news/glava-dis-group-pavel-likhnitskij-prinyal-uchastie-v-kruglom-stole-komitete-sf-po-rinku-bolshih-dahhih/",
            date = curTime - 60_000 * nextLong(20, 200),
            text = "2 апреля на площадке Совета Федерации прошло совещание, посвященное вопросам развития рынка больших данных, реализации нацпроекта «Экономика данных» и создания новых регуляторных механизмов. В мероприятии наряду с чиновниками из Минцифры и Минпромторга приняли участие представители бизнеса.\n" +
                    "\n" +
                    "По словам заместителя председателя Комитета Совета Федерации по экономической политике Константина Долгова, в Совете Федерации уделяют серьезное внимание развитию рынка данных. «Данные – это большая ценность, которая может стать основой для экономического роста в стране», – отметил сенатор.\n" +
                    "\n" +
                    "В настоящее время различные ведомства прилагают максимальные усилия в направлении внедрения дата-центричных подходов в государственном управлении и в ряде отраслей экономики. В данный момент Минцифры работает над созданием стандарта в области больших данных, отметил замминистра цифрового развития, связи и массовых коммуникаций Григорий Борисенко.\n" +
                    "\n" +
                    "Представители бизнеса со своей стороны рассказали о возможностях сотрудничества в области разработки и внедрения отечественных решений для управления данными, а также выразили готовность помочь с решением основных проблем в области развития ИТ-инфраструктуры на федеральном и региональном уровнях."
        ),
    )

    private val maleNames = listOf(
        "Мартынов Евгений Даниилович",
        "Блинов Александр Тихонович",
        "Калинин Юрий Даниилович",
        "Поздняков Даниил Андреевич",
        "Комаров Егор Денисович",
        "Иванов Пётр Максимович",
        "Козлов Роман Даниилович",
        "Романов Андрей Павлович",
        "Столяров Эмиль Олегович",
        "Орлов Павел Миронович",
        "Тихомиров Сергей Максимович",
        "Щукин Тимофей Владиславович",
        "Севастьянов Тимофей Кириллович",
        "Петров Ярослав Тимурович",
        "Шубин Кирилл Максимович",
        "Орлов Лев Тимурович",
        "Прокофьев Владимир Львович",
        "Масленников Николай Андреевич",
        "Смирнов Вадим Даниилович",
        "Иванов Матвей Александрович",
        "Карпов Роман Макарович",
        "Плотников Степан Давидович",
        "Морозов Матвей Михайлович",
        "Трофимов Тимур Матвеевич",
        "Николаев Максим Александрович",
        "Попов Андрей Фёдорович",
        "Беляев Давид Миронович",
        "Сурков Владислав Дмитриевич",
        "Ерофеев Никита Максимович",
        "Тимофеев Максим Максимович",
        "Щербаков Матвей Ильич",
        "Виноградов Дмитрий Маркович",
        "Федотов Михаил Даниэльевич",
        "Борисов Ибрагим Ильич",
        "Новиков Николай Даниэльевич",
        "Николаев Илья Русланович",
        "Николаев Платон Всеволодович",
        "Ковалев Михаил Васильевич",
        "Новиков Иван Максимович",
        "Смирнов Василий Дмитриевич",
        "Лебедев Максим Александрович",
        "Носов Григорий Ильич",
        "Барсуков Мирон Степанович",
        "Андрианов Максим Степанович",
        "Зверев Павел Семёнович",
        "Аксенов Марк Николаевич",
        "Акимов Лев Максимович",
        "Андреев Иван Анатольевич",
        "Федоров Иван Львович",
        "Новиков Максим Алексеевич"
    )

    private val femaleNames = listOf(
        "Сергеева Виктория Тихоновна",
        "Гусева Есения Руслановна",
        "Хромова Алёна Львовна",
        "Зайцева Ольга Глебовна",
        "Горбачева София Александровна",
        "Сорокина Оливия Степановна",
        "Анисимова Александра Никитична",
        "Еремеева Елизавета Кирилловна",
        "Сидорова Ангелина Семёновна",
        "Симонова Полина Данииловна",
        "Лазарева Вера Григорьевна",
        "Завьялова Анастасия Владиславовна",
        "Черная Анна Романовна",
        "Смирнова Алёна Владиславовна",
        "Басова Ева Константиновна",
        "Андреева Василиса Кирилловна",
        "Шубина Анастасия Михайловна",
        "Серова Маргарита Максимовна",
        "Ермакова София Егоровна",
        "Боброва Алёна Максимовна",
        "Климова Ирина Артёмовна",
        "Осипова Анастасия Дмитриевна",
        "Анисимова София Ивановна",
        "Смирнова Александра Михайловна",
        "Кулакова Яна Данииловна",
        "Коновалова Кристина Алексеевна",
        "Евсеева Анастасия Фёдоровна",
        "Кулешова Ангелина Александровна",
        "Маркова Яна Михайловна",
        "Калинина Эвелина Всеволодовна",
        "Чеснокова Мария Тихоновна",
        "Голубева Елизавета Романовна",
        "Зайцева Мария Мироновна",
        "Васильева Юлия Александровна",
        "Николаева Айлин Захаровна",
        "Комарова Ирина Тимуровна",
        "Тимофеева Кира Фёдоровна",
        "Киселева Карина Егоровна",
        "Фомина Полина Александровна",
        "Колосова Валерия Львовна",
        "Филатова Теона Арсентьевна",
        "Жукова Милана Дмитриевна",
        "Царева Виктория Артёмовна",
        "Вавилова Ангелина Мироновна",
        "Громова София Александровна",
        "Федотова Амина Михайловна",
        "Костина Варвара Викторовна",
        "Селезнева Валерия Данииловна",
        "Волкова Александра Леонидовна",
        "Пономарева Виктория Михайловна"
    )

    private val postsList = listOf(
        "Веб-разработчик",
        "Дефектолог",
        "Финансовый консультант",
        "Менеджер по маркетингу",
        "Исполнительный помощник",
        "Кредитный инспектор",
        "Системный аналитик",
        "Регистратор",
        "Системный архитектор",
        "Бухгалтер",
        "Главный инженер",
        "Специалист по компьютерной поддержке",
        "Арт-директор",
        "Оценщик стоимости",
        "Специалист по связям с клиентом",
        "Аналитик по исследованию рынка",
        "Главный технолог",
    )

    private fun generateDuties(count: Int = 5) = Array(count) {
        dutiesList.random()
    }.toList()

    private val dutiesList = listOf(
        "Обеспечение безопасности",
        "Контроль совместимости",
        "Руководство персоналом",
        "Контроль взаимодействия с заказчиком",
        "Обслуживание компьютерного оборудования",
        "Разработка и поддержка систем и сетей",
        "Обеспечение безопасности данных",
        "Защита от внешних угроз",
        "Решение проблем и устранение сбоев",
        "Консультирование пользователей",
        "Обучение персонала",
        "Ведение бухгалтерии",
        "Управление менеджментом",
        "Управление предпринимательской деятельностью",
        "Управление коммерческой деятельностью",
        "Планирование деятельности",
        "Планирование деятельности стратегических целей",
        "Контроль за разработкой",
        "Контроль за реализацией бизнес-планов",
        "Контроль соблюдения коммерческих условий",
        "Контроль за разработкой",
        "Анализ и решение организационно-технических проблем"
    )

    private fun generateEmail(): String {
        var email = ""
        repeat(Random.nextInt(10, 15)) {
            email += "1234567890qwertyuiopasdfghjklzxcvbnm".random()
        }
        val mail = when (Random.nextInt(0, 2)) {
            0 -> "yandex.ru"
            1 -> "gmail.com"
            else -> "mail.ru"
        }
        email += "@$mail"
        return email
    }

    private fun generatePhone(): String {
        fun randomCode() = Random.nextInt(100, 999)
        fun randomBody() = Random.nextInt(10, 99)
        return "+7 (${randomCode()}) ${randomCode()}-" +
                "${randomBody()}-${randomBody()}"
    }

    val employeesPresets = mutableStateListOf<Employee>().let { result ->
        maleNames.forEachIndexed { i, name ->
            val employee = Employee(
                avatarUrl = "https://randomuser.me/api/portraits/men/$i.jpg",
                departament = Departament.entries.random(),
                post = postsList.random(),
                duties = generateDuties(),
                email = generateEmail(),
                phone = generatePhone(),
                name = name
            )
            result.add(employee)
        }

        femaleNames.forEachIndexed { i, name ->
            val employee = Employee(
                avatarUrl = "https://randomuser.me/api/portraits/women/$i.jpg",
                departament = Departament.entries.random(),
                duties = generateDuties(),
                post = postsList.random(),
                email = generateEmail(),
                phone = generatePhone(),
                name = name,
            )
            result.add(employee)
        }

        // Устанавливаем 10 пользователям данные для входа
        result.take(10).forEachIndexed { i, user ->
            result[i] = user.copy(
                login = "user$i",
                password = "qwerty"
            )
        }

        // Добавляем админа
        result.add(
            Employee(
                avatarUrl = "https://randomuser.me/api/portraits/men/81.jpg",
                email = "admin@gmail.com",
                name = "Админов Админ Админович",
                phone = "89109109090",
                password = "qwerty",
                login = "admin",
                post = "Администратор",
                departament = Development_Department,
                duties = generateDuties(),
                role = ADMIN
            )
        )

        result.shuffle()

        return@let result
    }
}