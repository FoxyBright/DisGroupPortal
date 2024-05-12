package com.study.disgroupportal.data

import android.text.format.DateUtils.WEEK_IN_MILLIS
import com.study.disgroupportal.DisGroupPortalApp.Companion.curTime
import com.study.disgroupportal.model.news.New
import com.study.disgroupportal.model.portal.Departament
import com.study.disgroupportal.model.portal.Departament.Development_Department
import com.study.disgroupportal.model.requests.Request
import com.study.disgroupportal.model.requests.RequestStatus.OPENED
import com.study.disgroupportal.model.requests.RequestTheme.ARRANGEMENT
import com.study.disgroupportal.model.employee.Employee
import com.study.disgroupportal.model.employee.UserRole.ADMIN
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
            imageUrl = "https://cdnn21.img.ria.ru/images/07e8/03/1c/1936353739_0:0:3071:1728_640x0_80_0_0_86822cde6b7c031550c6d4b99c3df636.jpg.webp",
            title = "Под Душанбе задержали девять человек по делу об атаке на \"Крокус\"",
            link = "https://ria.ru/20240329/terakt-1936616469.html",
            date = curTime - 60_000 * nextLong(20, 200),
            text = "    Девять человек задержаны в Таджикистане, которых считают причастными к теракту в «Крокус Сити Холле», пишут Reuters и «РИА Новости» со ссылкой на источник.\n\n    " +
                    "Их также считают причастными к запрещенной в России террористической организации «Исламское государство Хорасан», которая взяла на себя ответственность за теракт.\n\n    " +
                    "Их задержали в городе Вахдат и доставили в Душанбе." +
                    "В России по делу о теракте в «Крокусе» проходят 12 человек. Восьмерых фигурантов уже арестовали, среди них четверо, кого следствие считает непосредственными участниками нападения: Далерджон Мирзоев, Саидакрами Рачабализода, Шамсидин Фаридуни и Мухаммадсобир Файзов. В салоне Renault, на которой передвигались подозреваемые в участии в теракте, нашли паспорта Таджикистана.\n\n    " +
                    "Родственники Фаридуни рассказали, что его родителей, жену, ребенка и других членов семьи увезли из дома в таджикском селе «люди в официальной одежде».\n\n    " +
                    "По делу также проходят Исроил Исломов с сыновьями Аминчоном и Диловаром. Следствие полагает, что те предоставили автомобиль и квартиру исполнителям нападения.\n\n    " +
                    "Еще один арестованный — Алишер Касимов. Мужчина сдавал трехкомнатную квартиру, так и познакомился с исполнителями теракта. Касимов подчеркивал, что не был в курсе о планах террористов: «Я не знал, они, оказывается, терроризм. Я через «Авито.ру» написал, что мне нужен квартирант, хочу сдавать квартиру». Мужчина заявил о своей невиновности и опротестовал свой арест.\n\n    " +
                    "По данным на вечер 27 марта, погибли 143 человека, пострадали более 180. По-прежнему крайне тяжелым остается состояние четырех пациентов. Как следует из свидетельств о смерти, террористы вели хаотичный огонь в упор и спину посетителям."
        ),

        New(
            imageUrl = "https://cdnn21.img.ria.ru/images/07e5/02/09/1596624913_0:132:3326:2003_640x0_80_0_0_896f86d7ddd9047eb077644712742d12.jpg.webp",
            title = "ВС России нанесли массированный удар по энергетическим объектам на Украине",
            link = "https://ria.ru/20240329/spetsoperatsiya-1936642273.html",
            date = curTime - 60_000 * nextLong(20, 200),
            text = "    Российская армия ночью ударила по энергетическим объектам на Украине, сообщило Минобороны в сводке о ходе спецоперации.\n\n    " +
                    "\"Вооруженными силами Российской Федерации нанесен групповой удар высокоточным оружием большой дальности воздушного," +
                    " морского и наземного базирования, в том числе аэробаллистическими гиперзвуковыми ракетами \"Кинжал\"," +
                    " а также беспилотными летательными аппаратами по объектам энергетики\", — говорится в публикации." + "\n\n    " +
                    "Кроме того, военные открыли огонь по объектам противовоздушной обороны ВСУ.\n\n    " +
                    "Цели удара достигнуты, все объекты поражены, подчеркнули в министерстве." +
                    "Ночью на Украине объявили воздушную тревогу. СМИ передавали о взрывах в Днепропетровской," +
                    " Хмельницкой, Львовской, Ивано-Франковской, Черкасской областях. Министр энергетики страны Герман Галущенко заявил," +
                    " что под ударом оказались объекты генерации.\n\n    " +
                    "Национальная энергетическая компания \"Укрэнерго\" уточнила, что после взрывов повреждения получили электростанции в" +
                    " центре и на западе Украины. Позднее сообщалось о повреждении объектов критической инфраструктуры в Львовской," +
                    " Днепропетровской, Черкасской и Ивано-Франковской областях, а также в подконтрольной Киеву части Херсонской области.\n\n    " +
                    "В ответ на атаки ВСУ по гражданским объектам российские военные регулярно наносят удары по украинской инфраструктуре: " +
                    "объектам энергетики, оборонной промышленности, военного управления и связи. Пресс-секретарь президента Дмитрий Песков " +
                    "подчеркивал, что армия не бьет по жилым домам и социальным объектам."
        ),

        New(
            imageUrl = "https://cdnn21.img.ria.ru/images/07e8/01/0c/1921040439_0:0:3087:1736_640x0_80_0_0_292163f294ec9e3cdc5fd2cfdc4d76bf.jpg.webp",
            title = "В Армении запретили передачи Соловьева",
            link = "https://ria.ru/20240329/armeniya-1936562641.html",
            date = curTime - 60_000 * nextLong(20, 200),
            text = "    В Армении запретили трансляцию передач, которые ведет российский журналист Владимир Соловьев," +
                    " сообщили в Телевизионной и радиовещательной сети страны.\n\n    " +
                    "\"Телевизионная и радиовещательная сеть Армении заблокировала на территории Республики " +
                    "Армения трансляцию авторских передач \"Вечер с Владимиром Соловьевым\" и \"Воскресный вечер с" +
                    " Владимиром Соловьевым\", транслируемых на телеканале \"РТР-Планета\" по общественному мультиплексу\", — " +
                    "говорится в публикации в Facebook*." +
                    "Ведомство уточнило, что приняло решение из-за \"постоянных нарушений\" статей межправительственного договора между Ереваном и Москвой о сотрудничестве в сфере массовых коммуникаций. При этом оно не привело конкретных примеров.\n\n    " +
                    "Как напомнила главный редактор медиагруппы \"Россия сегодня\" и телеканала RT Маргарита Симоньян, комментируя ситуацию в Telegram-канале, нынешнее руководство Армении приходило к власти под лозунгом \"Больше свободы слова\", но ее стало меньше.\n\n    " +
                    "\"Почему все свободолюбцы, придя к власти, оказываются диктаторами и обиженками? Загадка\", — добавила она.\n\n    " +
                    "В декабре комиссия по телевидению и радио Армении приостановила лицензию радиокомпании \"Тоспа\" — ретранслятора радио Sputnik Армения. В ведомстве уточнили, что возбудили два административных производства. Первое касалось выхода в эфир 17 ноября передачи \"Пятница с Тиграном Кеосаяном\". Второе административное производство инициировали из-за программы \"Абовян Тайм\"." +
                    "Российское посольство выразило сожаление из-за такого шага Еревана, который сказался бы на праве армянского слушателя выбирать источник информации.\n\n    " +
                    "Спустя месяц комиссия сняла запрет на вещание Sputnik Армения.\n\n    " +
                    "* Деятельность Meta (соцсети Facebook и Instagram) запрещена в России как экстремистская."
        ),

        New(
            imageUrl = "https://cdnn21.img.ria.ru/images/07e8/02/1a/1929551909_0:162:3068:1888_640x0_80_0_0_6c4316d73eee286f144966615deeb9c1.jpg.webp",
            title = "Роструд напомнил о шестидневной рабочей неделе в апреле",
            link = "https://ria.ru/20240329/rostrud-1936633568.html",
            date = curTime - 60_000 * nextLong(20, 200),
            text = "    Россиян ждет шестидневная рабочая неделя в конце апреля, сообщил Роструд в Telegram-канале.\n\n    " +
                    "\"Нас ожидает шестидневная рабочая неделя, с 22 по 27 апреля включительно\", — напомнили в ведомстве." +
                    "Уточняется, что после этого последуют четыре выходных дня подряд.\n\n    " +
                    "Роструд также напомнил, что 12 апреля в России отмечают День космонавтики, 19 —День работников государственной службы занятости населения, а 20 — Национальный день донора.\n\n    " +
                    "В августе прошлого года правительство приняло постановление, согласно которому в 2024-м выходной день с субботы 6 января переносится на пятницу 10 мая, с субботы 27 апреля — на понедельник 29 апреля, с субботы 2 ноября — на вторник 30 апреля.\n\n    " +
                    "Таким образом, отдыхать россияне будут с 28 апреля по 1 мая и с 9 по 12 мая. Плюс стандартные субботы и воскресенья — 4, 5, 11, 12, 18, 19, 25, 26."
        ),

        New(
            imageUrl = "https://cdnn21.img.ria.ru/images/07e8/03/1c/1936344448_0:120:1280:840_640x0_80_0_0_9e290c6e6f4625d365c27a0d01f9f29f.jpg.webp",
            title = "Рота ВСУ сдалась в плен, увидев работу дронов \"Джокер\"",
            link = "https://ria.ru/20240329/drony-1936517568.html",
            date = curTime - 60_000 * nextLong(20, 200),
            text = "    Российские FPV-дроны \"Джокер\" в ходе спецоперации заставили сдаться в плен роту ВСУ, заявил РИА Новости гендиректор предприятия-разработчика данных беспилотников \"Центр комплексных беспилотных решений\" (ЦКБР) Дмитрий Кузякин.\n\n    " +
                    "\"Наверное, уже никто не сможет повторить этот \"рекорд\". Это был не Bradley, не Humvee и даже не Leopard-2, а сразу 60 солдат ВСУ, которые были вынуждены сдаться в плен, увидев воочию, что значит массовое применение FPV дронов. <...> То есть один маленький дрон \"взял в плен\" целую роту ВСУ\", — сказал он, отвечая на вопрос, какова была самая важная цель, которую поразили \"Джокеры\" на поле боя." +
                    "Кузякин пояснил, что в тот момент украинские военные встали в классическую линию обороны, ожидая \"конвенциональной войны\": пулеметные гнезда, противотанковые расчеты, РПГ.\n\n    " +
                    "\"Наши же бойцы, которых мы подготовили и обеспечили техникой, вместо того чтобы начать \"играть по правилам\", на бой вообще не пришли, а пустили в ход FPV-расчеты, которые \"размотали\" сразу вторую линию украинской обороны. Украинские солдаты на передовой поняли, что сопротивление просто бессмысленно. Их массовая сдача в плен и есть главный трофей в работе нашего центра\", — подчеркнул разработчик.\n\n    " +
                    "ЦКБР (резидент технопарка ЦАГИ, Жуковский) занимается полным циклом вопросов боевого применения FPV-систем: от обучения пилотированию и эффективному применению до производства самих дронов.\n\n    " +
                    "За годы работы компанией создана линейка боевых FPV-дронов \"Джокер\". Последняя на сегодняшний день модель \"Джокер-10\" несет полезную нагрузку до пяти килограммов, развивая скорость до 100 километров в час (до 200 километров в час без полезной нагрузки). Научный \"костяк\" центра — выпускники МФТИ."
        ),

        New(
            imageUrl = "https://cdnn21.img.ria.ru/images/07e8/03/1b/1936135666_0:0:3062:1723_640x0_80_0_0_4f7db0cd3c738009e5394e8cf1259e5e.jpg.webp",
            title = "Южнокорейская газета извинилась за карикатуру на теракт в \"Крокусе\"",
            link = "https://ria.ru/20240329/karikatura-1936527536.html",
            date = curTime - 60_000 * nextLong(20, 200),
            text = "    Южнокорейская газета The Korea Herald извинилась за карикатуру на теракт в подмосковном \"Крокус Сити Холле\", которую ранее раскритиковало посольство России в Сеуле, извинения были опубликованы в печатной версии издания.\n\n    " +
                    "Ранее посольство России в Южной Корее назвало кощунственной и циничной карикатуру англоязычной газеты The Korea Herald на теракт в \"Крокусе\", заявив, что она оскорбляет чувства большинства южнокорейских граждан, разделяющих с россиянами боль этой трагедии." +
                    "\"The Korea Herald выражает глубокие соболезнования жертвам трагической террористической атаки в \"Крокус Сити Холле\" 22 марта вместе с народом и разделяет эту необъятную скорбь. В этой связи мы глубоко сожалеем о публикации синдицированной карикатуры в номере газеты от 27 марта. Мы сожалеем об этой оплошности, признаем и уважаем чувства тех, кто был оскорблен данной карикатурой\", — говорится в заметке, опубликованной на первой странице печатной версии газеты, где изначально была опубликована карикатура.\n\n    " +
                    "Издание еще раз повторило, что осознает свою ответственность за публикуемые материалы и выражает \"глубочайшие сожаления\".\n\n    " +
                    "Стрельба и пожар произошли вечером 22 марта перед концертом в \"Крокус Сити Холле\" в подмосковном Красногорске. Корреспондент РИА Новости, ставший свидетелем произошедшего, сообщал, что в зал ворвались несколько мужчин в камуфляже и без масок, они расстреливали людей в упор и бросали зажигательные шашки. По последним данным МЧС, погибли 143 человека.\n\n    " +
                    "Горящая крыша концертного зала Крокус Сити Холл - РИА Новости, 1920, 29.03.2024\n\n    " +
                    "Они не должны были" +
                    "Четырех предполагаемых исполнителей теракта задержали 23 марта в Брянской области. Впоследствии Далерджон Мирзоев, Муродали Рачабализода, Фаридуни Шамсиддин и Мухаммадсобир Файзов были арестованы в Москве по обвинению в совершении теракта, повлекшего смерть, в составе организованной группы (пункт \"б\" части 3 статьи 205 УК России).\n\n    " +
                    "Президент России Владимир Путин отмечал, что всего задержаны 11 человек и силовики работают над вскрытием всей пособнической базы террористов. По его словам, теракт был совершен руками радикальных исламистов. Директор ФСБ Александр Бортников отмечал, что одни исламисты не смогли бы подготовить этот теракт, им помогали. Он отметил, что первичные данные от задержанных подтверждают украинский след, в российских спецслужбах думают, что за терактом стоят США, Британия и Украина.\n\n    " +
                    "Путин отмечал, что Россия столкнулась не просто с тщательно цинично спланированным терактом, а с подготовленным и организованным массовым убийством мирных людей. Президент подчеркнул, что все исполнители, организаторы и заказчики этого преступления понесут справедливое и неизбежное наказание."
        )
    )

    val employeesPresets = listOf(
        Employee(
            avatarUrl = "https://randomuser.me/api/portraits/men/81.jpg",
            email = "ivan@gmail.com",
            name = "Иван Админович",
            phone = "89109109090",
            password = "qwerty",
            login = "ivan",
            post = "Администратор",
            departament = Development_Department,
            role = ADMIN,
//            duty = Duty(
//                dutyOne = "Обеспечение безопасности",
//                dutyTwo = "Контроль совместимости",
//                dutyThree = "Руководство персоналом",
//                dutyFour = "Контроль взаимодействия с заказчиком",
//                dutyFive = "Управление администраторской панелью приложения",
//            )
        ),

        Employee(
            avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/2/2a/%D0%9C%D0%B8%D1%85%D0%B0%D0%B8%D0%BB_%D0%9C%D0%B8%D1%88%D1%83%D1%81%D1%82%D0%B8%D0%BD_%2830-03-2022%29_%28cropped%29.jpg",
            name = "Михаил Владимирович Мишустин",
            post = "Председатель Правительства Российской Федерации",
            departament = Departament.entries.random(),
        ),
        Employee(
            avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Vladimir_Kolokoltsev_%282020-02-21%29.jpg/656px-Vladimir_Kolokoltsev_%282020-02-21%29.jpg?20200221142310",
            name = "Владимир Александрович Колокольцев",
            post = "Министр внутренних дел Российской Федерации",
            departament = Departament.entries.random(),
        ),
        Employee(
            avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/%D0%A1%D0%B5%D1%80%D0%B3%D0%B5%D0%B9_%D0%9B%D0%B0%D0%B2%D1%80%D0%BE%D0%B2_%2818-11-2022%29_%28cropped%29.jpg/375px-%D0%A1%D0%B5%D1%80%D0%B3%D0%B5%D0%B9_%D0%9B%D0%B0%D0%B2%D1%80%D0%BE%D0%B2_%2818-11-2022%29_%28cropped%29.jpg",
            name = "Сергей Викторович Лавров",
            post = "Министр иностранных дел Российской Федерации",
            departament = Departament.entries.random(),
        ),
        Employee(
            avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Sergey_Kravtsov_12.03.2020.jpg/375px-Sergey_Kravtsov_12.03.2020.jpg",
            name = "Сергей Сергеевич Кравцов",
            post = "Министр просвещения Российской Федерации",
            departament = Departament.entries.random(),
        ),
        Employee(
            avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d0/Olga_Liubimova_%282020-12-07%29.jpg/300px-Olga_Liubimova_%282020-12-07%29.jpg",
            name = "Ольга Борисовна Любимова",
            post = "Министр культуры Российской Федерации",
            departament = Departament.entries.random(),
        ),
        Employee(
            avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6f/Mikhail_Murashko_%282020-01-29%29.jpg/411px-Mikhail_Murashko_%282020-01-29%29.jpg",
            name = "Михаил Альбертович Мурашко",
            post = "Министр здравоохранения Российской Федерации",
            departament = Departament.entries.random(),
        ),
        Employee(
            avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Anton_Siluanov_%282019-09-25%29.jpg/315px-Anton_Siluanov_%282019-09-25%29.jpg",
            name = "Антон Германович Силуанов",
            post = "Министр финансов Российской Федерации",
            departament = Departament.entries.random(),
        ),
        Employee(
            avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/32/Official_portrait_of_Sergey_Shoigu.jpg/345px-Official_portrait_of_Sergey_Shoigu.jpg",
            name = "Сергей Кужугетович Шойгу",
            post = "Министр обороны Российской Федерации",
            departament = Departament.entries.random(),
        ),
        Employee(
            avatarUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Konstantin_Chuychenko_official_portrait.png/300px-Konstantin_Chuychenko_official_portrait.png",
            name = "Константин Анатольевич Чуйченко",
            post = "Министр юстиции Российской Федерации",
            departament = Departament.entries.random(),
        )
    )
}