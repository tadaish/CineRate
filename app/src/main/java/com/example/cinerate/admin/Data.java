package com.example.cinerate.admin;

import com.example.cinerate.models.Genre;
import com.example.cinerate.models.Language;
import com.example.cinerate.models.Movie;
import com.example.cinerate.models.User;

public class Data {
    public static void loadData(){
        //Viễn tưởng
        AdminHomeActivity.movieDAO.addMovie(new Movie("Avengers: Endgame",
                "Sau những sự kiện tàn khốc trong Avengers: Infinity War, vũ trụ bị hủy hoại do những nỗ lực của Thanos. Với sự giúp đỡ của các đồng minh còn lại, Avengers phải tập hợp lại một lần nữa để đảo ngược hành động của Thanos và khôi phục trật tự cho vũ trụ vĩnh viễn, bất kể hậu quả có thể xảy ra.",
                2019, "Joe Russo, Anthony Russo",
                "https://image.tmdb.org/t/p/w342/or06FN3Dka5tukK1e9sl16pB3iy.jpg", "Robert Downey Jr", "https://www.youtube.com/watch?v=hA6hldpSTF8",
                2, 5));

        AdminHomeActivity.movieDAO.addMovie(new Movie("Godzilla x Kong: The New Empire",
                "Kong và Godzilla - hai sinh vật vĩ đại huyền thoại, hai kẻ thủ truyền kiếp sẽ cùng bắt tay thực thi một sứ mệnh chung mang tính sống còn để bảo vệ nhân loại, và trận chiến gắn kết chúng với loài người mãi mãi sẽ bắt đầu.",
                2024, "Adam Wingard",
                "https://image.tmdb.org/t/p/w342/z1p34vh7dEOnLDmyCrlUVLuoDzd.jpg", "Rebecca Hall, Brian Tyree Henry, Dan Stevens", "https://youtu.be/UDiIfaWVxwA?si=FcSCUbl1jDv928Sh",
                2, 5));

        AdminHomeActivity.movieDAO.addMovie(new Movie("Dune",
                "Vào năm 10.191, thế giới đang nổ ra cuộc chiến tranh giành quyền kiểm soát hành tinh sa mạc Dune — nơi duy nhất có thể tìm thấy chất du hành thời gian 'Spice'. Nhưng khi một nhà lãnh đạo từ bỏ quyền kiểm soát, điều đó chỉ để anh ta có thể tổ chức một cuộc đảo chính với một số nhân vật không có lợi.",
                1984, "David Lynch",
                "https://image.tmdb.org/t/p/w342/ldugBX89jCQA9RRwfzRgX0gNpBc.jpg", "Kyle MacLachlan, Patrick Stewart, Franesca Annis", "https://youtu.be/WHh8dzxTSNw?si=KEf6lgSaYtuiO2Rr",
                2, 5));

        AdminHomeActivity.movieDAO.addMovie(new Movie("The Platform",
                "Phiến bê tông chứa thức ăn đi xuống từng tầng một trong nhà tù. Tù nhân ở trên sẽ được ăn no, những kẻ bên dưới sẽ chết đói trong tuyệt vọng. Và cuộc nổi loạn sắp xảy ra.",
                2019, "Galder Gaztelu-Urrutia",
                "https://image.tmdb.org/t/p/w342/8ZX18L5m6rH5viSYpRnTSbb9eXh.jpg", "Ivan Massague, Zorion Eguileor, Antonia San Juan", "https://youtu.be/Bsyicj2qdqs?si=a2n36ciT9yyW9H6U",
                6, 5));

        AdminHomeActivity.movieDAO.addMovie(new Movie("The Platform",
                "Phiến bê tông chứa thức ăn đi xuống từng tầng một trong nhà tù. Tù nhân ở trên sẽ được ăn no, những kẻ bên dưới sẽ chết đói trong tuyệt vọng. Và cuộc nổi loạn sắp xảy ra.",
                2019, "Galder Gaztelu-Urrutia",
                "https://image.tmdb.org/t/p/w342/8ZX18L5m6rH5viSYpRnTSbb9eXh.jpg", "Ivan Massague, Zorion Eguileor, Antonia San Juan", "https://youtu.be/Bsyicj2qdqs?si=a2n36ciT9yyW9H6U",
                6, 5));

        AdminHomeActivity.movieDAO.addMovie(new Movie("Men in Black 1",
                "Men In Black kể về hoạt động của 1 tổ chức bí mật chuyên quản lý, giám sát những người ngoài hành tinh trên trái đất (MIB)- Men In Black. Tình cờ chứng kiến một người ngoài hành tinh, nhân viên cảnh sát James Edwards bỗng chốc trở thành đặc vụ J của MIB. Đi cùng với J là K - một đặc vụ kỳ cựu. Cùng lúc đó, một sinh vật ngoài hành tinh xuất hiện để đi tìm 1 Thiên hà siêu nhỏ mang sức mạnh vô tận được gắn trên vòng cổ của 1 con mèo mà chủ nhân của con mèo là hoàng tử thuộc hoàng gia Arquillia. Hoàng tử bị con gián ngoài hành tinh giết và tàu chiến của Arquillia muốn huỷ diệt Trái Đất để ngăn không cho Thiên Hà vào tay Sinh vật kia. J và K phải đứng trước 1 nhiệm vụ đầy khó khăn...và họ sẽ phải làm gì để bảo vệ Trái Đất???",
                1997, "Barry Sonnenfeld",
                "https://image.tmdb.org/t/p/w342/uLOmOF5IzWoyrgIF5MfUnh5pa1X.jpg", "Tommy Lee Jones, Will Smith", "https://youtu.be/CqCkDKS1E2c?si=A9SPlai9-Zg3eiIL",
                2, 5));

        AdminHomeActivity.movieDAO.addMovie(new Movie("Deadpool & Wolverine",
                "Đôi bạn \"thân\" lần đầu tiên kết hợp trên màn ảnh!",
                2024, "Shawn Levy",
                "https://image.tmdb.org/t/p/w342/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg", "Ryan Reynolds, Hugh Jackman, Emma Corrin", "https://youtu.be/CqCkDKS1E2c?si=A9SPlai9-Zg3eiIL",
                2, 5));

        //Hoạt hình
        AdminHomeActivity.movieDAO.addMovie(new Movie("SPY x FAMILY CODE: White",
                "Spy x Family là tựa phim chuyển thể từ manga đình đám cùng tên của tác giả Tatsuya Endo. Trong khi Loid và Yor là hai đặc vụ chuyên nghiệp hợp tác trở thành vợ chồng thì cô bé Anya và cún Bond đều giữ bí mật về năng lực đặc biệt của riêng mình. Đại gia đình điệp viên 3 người 1 cún nguỵ trang và cùng nhau hoàn thành những nhiệm vụ khó nhằn.\n" +
                        "\n" +
                        "Trong bộ phim lần này, sau khi nhận được yêu cầu thay thế mình trong Chiến dịch Strix, Loid Forger quyết định giúp con gái Anya chiến thắng trong cuộc thi nấu ăn tại Học viện Eden bằng cách nấu bữa ăn yêu thích của hiệu trưởng để tránh bị thay thế khỏi nhiệm vụ mật. Nhưng từ đây, gia đình Forger phát hiện ra bí mật kinh hoàng ảnh hưởng đến hòa bình thế giới.",
                2023, "Takashi Katagiri",
                "https://image.tmdb.org/t/p/w342/xlIQf4y9eB14iYzNN142tROIWON.jpg", "Takuya Eguchi, Atsumi Tanezaki, Saori Hayami", "https://youtu.be/IshZrRvWB80",
                3, 4));

        AdminHomeActivity.movieDAO.addMovie(new Movie("Doraemon: Nobita và bản giao hưởng Địa Cầu ",
                "Chuẩn bị cho buổi hòa nhạc ở trường, Nobita đang tập thổi sáo - nhạc cụ mà cậu dở tệ. Thích thú trước nốt \"No\" lạc quẻ của Nobita, Micca - cô bé bí ẩn đã mời Doraemon, Nobita cùng nhóm bạn đến \"Farre\" - Cung điện âm nhạc tọa lạc trên một hành tinh nơi âm nhạc sẽ hóa thành năng lượng. Nhằm cứu cung điện này, Micca đang tìm kiếm \"virtuoso\" - bậc thầy âm nhạc sẽ cùng mình biểu diễn! Với bảo bối thần kì \"chứng chỉ chuyên viên âm nhạc\", Doraemon và các bạn đã chọn nhạc cụ, cùng Micca hòa tấu, từng bước khôi phục cung điện. Tuy nhiên, một vật thể sống đáng sợ sẽ xóa số âm nhạc khỏi thế giới đang đến gần, Trái Đất đang rơi vào nguy hiểm... ! Liệu những người bạn nhỏ có thể cứu được \"tương lai âm nhạc\" và cả địa cầu này?",
                2024, "Kazuaki Imai",
                "https://image.tmdb.org/t/p/w342/llyJNci45ABJkiGMw819U0tpRzT.jpg", "Wasabi Mizuta, Megumi Oohara", "https://youtu.be/fAfFd2jRT0U?si=ybPr7rT7gYkIM9li",
                3, 4));

        AdminHomeActivity.movieDAO.addMovie(new Movie("Kung Fu Panda 4",
                "Sau khi Po được chọn trở thành Thủ lĩnh tinh thần của Thung lũng Bình Yên, Po cần tìm và huấn luyện một Chiến binh Rồng mới, trong khi đó một mụ phù thủy độc ác lên kế hoạch triệu hồi lại tất cả những kẻ phản diện mà Po đã đánh bại về cõi linh hồn.",
                2024, "Mike Mitchell",
                "https://image.tmdb.org/t/p/w342/kDp1vUBnMpe8ak4rjgl3cLELqjU.jpg", "Jack Black, Awkwafina, Bryan Cranston, Viola Davis", "https://youtu.be/FY5EI7Sg63g",
                2, 4));

        AdminHomeActivity.movieDAO.addMovie(new Movie("Thám tử lừng danh Conan: Tàu ngầm sắt màu đen",
                "Thám Tử Lừng Danh Conan: Tàu Ngầm Sắt Màu Đen lấy bối cảnh tại Pacific Buoy - một trụ sở hàng hải của Interpol có nhiệm vụ kết nối các camera an ninh trên toàn thế giới. Nhóm của Conan, theo lời mời của Sonoko, cũng đến đảo Hachijo để xem cá voi. Tại đây, Conan nhận được thông tin về một nhân viên Europol bị ám sát. Cùng với đó, tính mạng Haibara bị đe dọa, phải chăng thân phận của cô đã bị bại lộ trước Gin…",
                2023, "Yuzuru Tachikawa",
                "https://image.tmdb.org/t/p/w342/gLaJOggH5fP0DyeI7Jkpr78B0lc.jpg", "Minami Takayama, Wakana Yamazki, Rikiya Koyama, Shuuichi Ikeda", "https://youtu.be/FXgdEb4kPR4?si=2TsyK8m63wnKquCZ",
                3, 4));

        AdminHomeActivity.movieDAO.addMovie(new Movie("Klaus",
                "Viên bưu tá ích kỉ và người thợ làm đồ chơi ẩn dật bắt tay trở thành đôi bạn lạ kì, mang tới cho một thị trấn lạnh lẽo và u ám niềm vui mà nơi đó cần đến vô ngần.",
                2019, "Sergio Pablos",
                "https://image.tmdb.org/t/p/w342/q125RHUDgR4gjwh1QkfYuJLYkL.jpg", "Rashida Jones, J.K. Simmons, Joan Cusack", "https://youtu.be/taE3PwurhYM",
                2, 4));

        AdminHomeActivity.movieDAO.addMovie(new Movie("The Super Mario Bros. Movie",
                "The Super Mario Bros. Movie xoay quanh cuộc phiêu lưu ở thế giới Vương quốc Nấm của anh em thợ sửa ống nước Mario và Luigi. Sau khi tình cờ bước tới vùng đất lạ từ một ống nước, Luigi đã bị chia tách với Mario và rơi vào tay quái vật rùa xấu xa Bowser, hắn đang âm mưu độc chiếm thế giới. Trong lúc cố gắng tìm kiếm người anh em của mình, Mario đã chạm mặt anh bạn nấm Toad và công chúa Peach. Từ đây họ sát cánh bên nhau trên hành trình ngăn chặn thế lực hắc ám.",
                2023, "Aaron Horvath, Michael Jelenic",
                "https://image.tmdb.org/t/p/w342/qNBAXBIQlnOThrVvA6mA2B5ggV6.jpg", "Chris Patt, Charlie Day, Anya Taylor-Joy", "https://youtu.be/LTFGH0rJ-EY",
                2, 4));

        AdminHomeActivity.movieDAO.addMovie(new Movie("Puss in Boots: The Last Wish",
                "Mèo Đi Hia Puss nay chỉ còn lại 1 mạng sống cuối cùng trong số 9 mạng mà cậu từng có. Và để lấy lại những gì đã mất, cậu cùng cô mèo Kitty Softpaws và người bạn đồng hành mới - chú chó Perro, sẽ cùng nhau tiến vào khu rừng Đen để tìm Ngôi Sao Ước huyền thoại",
                2022, "Joel Crawford",
                "https://image.tmdb.org/t/p/w342/kuf6dutpsT0vSVehic3EZIqkOBt.jpg", "Antonia Banderas, Salma Hayek, Harvey Guillen", "https://youtu.be/fAfFd2jRT0U?si=ybPr7rT7gYkIM9li",
                2, 4));

        //Chiến tranh
        AdminHomeActivity.movieDAO.addMovie(new Movie("The Pianist",
                "Wladyslaw Szpilman là một nhạc công dương cầm tài năng người Do Thái Ba Lan nhưng cuộc đời bị biến đổi bởi chính sách bắt bớ của quân Phát xít Đức trong Thế chiến thứ hai.\n" +
                        "\n" +
                        "Gia đình anh bị bắt đi trại tập trung trong khi anh may mắn trốn thoát nhưng phải lẩn trốn không ngừng trước sự truy lùng ráo riết của quân lính. Tuy sống một cuộc sống tù túng, chui lủi, trong anh vẫn đầy nhiệt huyết, niềm đam mê cháy bỏng với âm nhạc.",
                2002, "Roman Polanski",
                "https://image.tmdb.org/t/p/w342/2hFvxCCWrTmCYwfy7yum0GKRi3Y.jpg", "Adrien Brody, Thomas Kretschmann", "https://youtu.be/u_jE7-6Uv7E?si=TzvPo-21wgZvzLXP",
                2, 7));

        AdminHomeActivity.movieDAO.addMovie(new Movie("Giải cứu binh nhì Ryan",
                "Phim lấy bối cảnh chiến tranh trong thế giới thứ 2 kể về 1 đội biệt kích Mỹ do đại úy John H. Miller dẫn đầu đang cố gắng giải cứu 1 chiến hữu của mình là binh nhì James Francis Ryan thuộc đội nhảy dù đang bị kẹt trong căn cứ địch. Ngoài ra, họ còn được biết Ryan là con của 1 gia đình có 4 anh em đều đi lính mà hết 3 người đã hy sinh, chỉ còn lại duy nhất Ryan còn sống sót nên cả đội quyết tâm cứu cho được Ryan để anh về đoàn tụ với gia đình...",
                1998, "Steven Spielberg",
                "https://image.tmdb.org/t/p/w342/uqx37cS8cpHg8U35f9U5IBlrCV3.jpg", "Steven Spielberg", "https://youtu.be/RYExstiQlLc",
                2, 7));

        AdminHomeActivity.movieDAO.addMovie(new Movie("Phía Tây không có gì lạ",
                "Khi thiếu niên 17 tuổi Paul tham gia Mặt trận phía Tây trong Thế chiến I, niềm phấn khích ban đầu của cậu sớm vỡ vụn trước thực tế nghiệt ngã của cuộc sống nơi chiến hào.",
                2022, "Edward Berger",
                "https://image.tmdb.org/t/p/w342/2IRjbi9cADuDMKmHdLK7LaqQDKA.jpg", "Felix Kammerer, Albrecht Schuch, AaronHilmer", "https://youtu.be/hf8EYbVxtCY",
                7, 7));

        AdminHomeActivity.movieDAO.addMovie(new Movie("T-34",
                "Năm 1944, một nhóm binh sĩ Nga can đảm đã trốn thoát khỏi sự giam cầm của Đức trong chiếc xe tăng T-34 huyền thoại đã bị phá hủy một nửa. Đó là những lần dũng cảm khó quên, chiến đấu khốc liệt, tình yêu không thể phá vỡ và là một phép màu...",
                2018, "Aleksey Sidorov",
                "https://image.tmdb.org/t/p/w342/7oZA31r9NzVGBZOKs5qmKwZyraZ.jpg", "Alexander Petrov, Victor Dobronravov, Irina Starshenbaum, Vinzenz Kiefer", "https://youtu.be/D1UDvi5xL9w",
                5, 7));

        //Kinh dị

        AdminHomeActivity.movieDAO.addMovie(new Movie("The Exorcist",
                "Phim kinh dị nhất mọi thời đại \"The Exorcist\" dựa theo tiểu thuyết cùng tên của nhà văn Blatty từ một trường hợp có thật xảy ra tại Washington, nói về bé gái Regan sống cùng mẹ nhưng em có những biểu hiện như quỷ ám sau khi chơi cầu cơ...\n" +
                        "\n" +
                        "Ở đầu phim, Regan là một cô bé ngây thơ, xinh đẹp và khá tự chủ. Tình hình trở nên xấu đi sau khi Regan nghe thấy những tiếng động lạ, có những biểu hiện tục tĩu, bạo lực sau những cơn cáu giận bất thường. Khi mà mọi việc có chiều hướng vượt quá tầm kiểm soát cũng như trình độ của các bác sĩ chữa trị cho Regan, họ đành nhờ đến một sự trợ giúp mang yếu tố thần linh.",
                1973, "William Friedkin",
                "https://image.tmdb.org/t/p/w342/5x0CeVHJI8tcDx8tUUwYHQSNILq.jpg", "Ellen Burstyn, Jason Miller, Max von Sydơ", "https://youtu.be/PkrN-l4G8U4",
                2, 1));

        AdminHomeActivity.movieDAO.addMovie(new Movie("Ringu 0",
                "Có một cuốn băng được người ta đồn là xem xong thì sẽ nhận một cú điện thoại và sau 7 ngày thì người đó lăn ra chết. Và cuốn băng đó đã rơi vào tay nhóm bạn trẻ và...\n" +
                        "Bộ Film nguyên gốc của Nhật Bản.",
                2000, "Norio Tsuruta",
                "https://image.tmdb.org/t/p/w342/nuhEWlaoMD00rTDCiYxdEc7d1tD.jpg", "Yukie Nakama, Seiichi Tanabe, Kumiko Aso", "https://youtu.be/ROQJ9eNpz5U?si=dvBsl1CqadRpUY1g",
                3, 1));

        AdminHomeActivity.movieDAO.addMovie(new Movie("https://youtu.be/x_TSgCjSoOQ",
                "Lấy bối cảnh năm 1971, phim The Conjuring kể về câu chuyện của Ed và Lorraine Warren là một trong những người chuyên điều tra về các hiện tượng siêu nhiên nổi tiếng vào thế kỉ 20.\n" +
                        "\n" +
                        "Một gia đình bị ma ám và liên tục bị khủng bố bởi một bóng ma kinh dị, gia đình này đã cầu xin sự giúp đỡ từ cặp đôi Warren. Tất nhiên, đây là nghề của họ nên Warren nhanh chóng nhận lời giúp đỡ. Tuy nhiên họ lại không hề biết rằng chính sự gật đầu này đã dẫn họ tiếp xúc với những thế lực siêu nhiên kì quái và trở thành một trong những vụ đáng sợ nhất mà họ từng gặp trong đời.",
                2013, "James Wan",
                "https://image.tmdb.org/t/p/w342/wVYREutTvI2tmxr6ujrHT704wGF.jpg", "Patrick Wilson, Vera Farmiga", "https://youtu.be/ROQJ9eNpz5U?si=dvBsl1CqadRpUY1g",
                2, 1));

        AdminHomeActivity.movieDAO.addMovie(new Movie("Talk to Me",
                "Một nhóm bạn phát hiện ra bàn tay ma quái cho phép họ triệu hồi các linh hồn bí ẩn. Họ dần bị cuốn vào trò chơi này mà không biết rằng một trong số họ đã đi quá xa và giải phóng thế lực hắc ám kinh hoàng.",
                2000, "Michael Philippou, Danny Philippou",
                "https://image.tmdb.org/t/p/w342/kdPMUMJzyYAc4roD52qavX0nLIC.jpg", "Sophia Wilde, Alexandra Jensen, Joe Bird", "https://youtu.be/x_TSgCjSoOQ",
                2, 1));

        //Hài
        AdminHomeActivity.movieDAO.addMovie(new Movie("Nhà có 5 nàng tiên",
                "Ông Tiên Cảnh và bà Mai nhỏ nhẹ là hai vợ chồng nghèo sống bằng nghề lượm ve chai, họ luôn mơ ước có một đứa con. Một hôm nọ, hai vợ chồng nhặt được năm bé gái mồ côi, họ thương yêu năm bé gái ấy như con ruột và nuôi đến khôn lớn, cho chúng ăn học đàng hoàng...",
                2013, "Trần Ngọc Giàu",
                "https://upload.wikimedia.org/wikipedia/vi/4/4e/Poster_nha_co_5_nang_tien.jpeg", "Hoài Linh, Việt Hương", "https://youtu.be/WrVlikNHjt0?si=1-5ko7mbauPJq7E6",
                1, 3));
        AdminHomeActivity.movieDAO.addMovie(new Movie("Bỗng dưng trúng số",
                "Người lính Hàn Quốc Chun Woo (Ko Kyoung-pyo) vô tình nhặt được tờ vé số trúng độc đắc trị giá lên đến gần 6 triệu đô! Nhưng chưa kịp vui mừng bao lâu, tờ vé số ấy không may bị cuốn bay sang bên kia biên giới và rơi vào tay anh lính Triều Tiên Yong Ho (Lee Yi-kyung). Chun Woo cần tờ vé số để đổi tiền, trong khi Yong Ho dù nắm giữ tờ vé quan trọng lại không thể đặt chân đến Hàn Quốc. Câu chuyện ngày càng trở nên rắc rối và hài hước khi có thêm những người đồng đội của hai anh chàng biết được sự việc và nhất quyết tham gia vào cuộc đàm phán chia tiền.",
                2022, "Park Gyu-tae",
                "https://image.tmdb.org/t/p/w342/AifHZptVzkzLLfk2tedIQixqrgV.jpg", "Ko Kyoung-pyo, Lee Yi-kyung, Park Se-wan", "https://www.youtube.com/watch?v=D3KbO3QF-lg",
                4, 3));
        AdminHomeActivity.movieDAO.addMovie(new Movie("Bà Thím Báo Thù",
                "Sau khi cửa hàng giặt là của mình bị cháy, bà mẹ đơn thân Deok-hee nhận được cuộc gọi từ ngân hàng đề nghị cho vay trả trước hậu hĩnh nên cô nhận ngay. Nhưng ngay sau đó, cô phát hiện ra rằng mình đã trở thành nạn nhân của một âm mưu lừa đảo bằng giọng nói. Vào lúc tuyệt vọng, Jae-min, kẻ lừa đảo đã lừa cô, lại gọi điện cho Deok-hee và kể về tổ chức tội phạm đã nhốt anh và ép anh thực hiện các cuộc gọi lừa đảo bằng giọng nói từ Trung Quốc. Với cuộc gọi đó, Deok-hee quyết định tự mình điều tra vụ án này và đáp máy bay đến Trung Quốc cùng bạn bè",
                2024, "Park Young-ju",
                "https://image.tmdb.org/t/p/w342/h9Kdc6o7v3d7tVXyT6QndVYlaeD.jpg", "Ra Mi-ran, Gong Myoung, Yum Hye-ran", "https://youtu.be/Cv2kg-pMWT4?si=2zIOu46_9v8f0ih4",
                4, 3));
        AdminHomeActivity.movieDAO.addMovie(new Movie("Giờ cao điểm",
                "Bộ film là sự kết hợp giữa anh chàng hài hước Chris Tucker và người của hành động Jackie Chan. Hai người họ đã cùng nhau chống lại kẻ xấu và rơi vào những tình thế nguy hiểm nhưng cũng rất buồn cười.",
                1998, "Brett Ratner",
                "https://image.tmdb.org/t/p/w342/3WsLE6FtxEPRa6U9sbY1ckrn39s.jpg", "Thành Long, Chris Tucker", "https://youtu.be/7-oN5IEjwIQ",
                2, 3));
        //Hành động
        AdminHomeActivity.movieDAO.addMovie(new Movie("Mad Max 2",
                "Phim nói về một cựu cảnh sát Úc hiện đang sống ở các vùng hẻo lánh nước Úc như một chiến binh đồng ý giúp một cộng đồng người sống sót đang sống trong một nhà máy lọc dầu để bảo vệ họ và các nguồn cung xăng của khỏi các chiến binh man rợ.",
                1981, "George Miller",
                "https://image.tmdb.org/t/p/w342/dhVekfpaCW3QavAwGYbaQig87Xc.jpg", "Mel Gibson, Bruce Spene", "https://youtu.be/UR4mhks--Do",
                2, 2));
        AdminHomeActivity.movieDAO.addMovie(new Movie("The Terminator",
                "Năm 2029, những kẻ cai trị Trái Đất quyết định gửi về quá khứ một robot sát thủ mang tên The Terminator - Kẻ Hủy Diệt để thay đổi quá khứ. Nhiệm vụ của tên robot này là giết chết Sarah Connor, người có vai trò quyết định trong cuộc kháng chiến của nhân loại sau này....",
                1984, "James Cameron",
                "https://image.tmdb.org/t/p/w342/qvktm0BHcnmDpul4Hz01GIazWPr.jpg", "Linda Hamilton, Michael Biehn,Arnold Schwarzenegger ", "https://youtu.be/QIcomuI1j7I",
                2, 2));
        AdminHomeActivity.movieDAO.addMovie(new Movie("Terminator 2: Judgment Day",
                "Sau thất bại ở phần 1, Skynet bằng gửi thêm một robot khác, mạnh mẽ hơn để tiêu diệt Sarah Connor và đứa con của bà, John Connor. Tuy nhiên, lần này hai mẹ con được bảo vệ bởi chính Kẻ Hủy Diệt trong phần 1, được phe kháng chiến sửa chữa và gửi về từ tương lai.",
                1991, "James Cameron",
                "https://image.tmdb.org/t/p/w342/5M0j0B18abtBI5gi2RhfjjurTqb.jpg", "Linda Hamilton, Michael Biehn,Arnold Schwarzenegger, Robert Patrick", "https://youtu.be/lwSysg9o7wE",
                2, 2));
        AdminHomeActivity.movieDAO.addMovie(new Movie("The Good, the Bad and the Ugly",
                "Blondie là tay cao bồi chuyên săn tiền thưởng ở miền Tây. Tuco là tên tội phạm đang lẩn trốn sự truy đuổi. Cả hai hợp tác kiếm tiền từ lệnh truy nã của Tuco.\n" +
                        "\n" +
                        "Cả hai phát hiện ra một kho vàng khi chạm trán đoàn xe bị cướp và lấy được thông tin từ kẻ sống sót cuối cùng.\n" +
                        "\n" +
                        "Tuco đã kịp nghe được tên của nghĩa địa chôn vàng, còn Blondie nhớ được tên ngôi mộ... Giờ đây, cả hai phải giữ mạng sống cho kẻ còn lại nếu muốn tìm được kho vàng. Cũng trong lúc đó, tên sát thủ Angel Eye đang trên đường tới nơi chôn giấu...\n" +
                        "\n" +
                        "Bộ phim được xếp hàng vào danh sách các phim cao bồi kinh điển. Bộ phim cũng ra nguồn gốc ra đời của hình ảnh đấu súng tay ba - 'Mexican standoff'.",
                1966, "Sergio Leone",
                "https://image.tmdb.org/t/p/w342/bX2xnavhMYjWDoZp1VM6VnU1xwe.jpg", "Clint Eastwood, Eli Wallach, Lee Van Cleef", "https://youtu.be/WCN5JJY_wiA",
                2, 2));
        //Lãng mạn
        AdminHomeActivity.movieDAO.addMovie(new Movie("ReLIFE",
                "ReLife được chuyển thể từ web manga cùng tên của tác giả Yayoiso. Arata Kaizaki sau 5 tháng đi làm ở một công ty, vì bảo vệ một đồng nghiệp mà anh đã phải nghỉ việc và trở thành kẻ thất nghiệp ở tuổi 27. Trong giai đoạn khủng hoảng đỉnh điểm, anh gặp Ryo Yoake, một người lạ mặt tự xưng đến từ dự án thí nghiệm mang tên ReLIFE – LÀM LẠI CUỘC ĐỜI. Điều gì sẽ xảy đến với tình yêu của đôi bạn trẻ? Và sau khi dự án LÀM LẠI CUỘC ĐỜI kết thúc, tương lai của Kaizaki sẽ ra sao? Bộ phim sẽ mang đến cho người xem một cái kết vô cùng bất ngờ và thú vị.",
                2017, "Takeshi Furusawa",
                "https://image.tmdb.org/t/p/w342/nd4qWfSWvsRo6EIrgpX1SVSG9bC.jpg", "Taishi Nakagawa, Yuna Taira, Mahiro Takasugi", "https://www.youtube.com/watch?v=m5FsR7eQZoA",
                3, 6));

        AdminHomeActivity.movieDAO.addMovie(new Movie("Call Me by Your Name",
                "Call Me By Your Name được chuyển thể từ tiểu thuyết cùng tên của nhà văn André Aciman, lấy bối cảnh những năm 80. Nội dung tập trung vào mối quan hệ đầy cảm hứng giữa cậu bé 17 tuổi Elio (Timothée Chalamet) và chàng sinh viên 24 tuổi Oliver (Armie Hammer).\n" +
                        "\n" +
                        "Oliver là sinh viên khảo cổ học người Mỹ đến ở trong biệt thự nhà Elio và theo học giáo sư là bố của cậu bé. Hai chàng trai trẻ đã bị cuốn vào một mối tình lãng mạn giữa khung hình tuyệt đẹp của miền quê nước Ý trong một mùa hè rực rỡ năm 1983.",
                2017, "Luca Guadagnino",
                "https://image.tmdb.org/t/p/w342/mZ4gBdfkhP9tvLH1DO4m4HYtiyi.jpg", "Timothée Chalamet, Armie Hammer, Michael Stuhlbarg", "https://youtu.be/Z9AYPxH5NTM?si=z8v4AOWk2pHog38t",
                2, 6));
        AdminHomeActivity.movieDAO.addMovie(new Movie("The Shape of Water",
                "Người Đẹp & Thủy Quái là một chuyện tình không tưởng lấy bối cảnh trong thời kỳ Chiến Tranh Lạnh 1962. Làm việc cho viện nghiên cứu bí mật được bảo vệ nghiêm ngặt của chính phủ, Elisa cảm thấy trống vắng, cô đơn. Rồi cuộc đời cô gái thay đổi mãi mãi khi cô và cộng sự Zelda khám phá ra một vụ thí nghiệm kinh hoàng - tạo ra thủy quái lốt người!",
                2017, "Guillermo del Toro",
                "https://image.tmdb.org/t/p/w342/9zfwPffUXpBrEP26yp0q1ckXDcj.jpg", "Sally Hawkins, Doug Jones, Michael Shannon", "https://youtu.be/XFYWazblaUA",
                2, 6));











        AdminHomeActivity.languageDAO.addLanguage(new Language("Tiếng Việt"));
        AdminHomeActivity.languageDAO.addLanguage(new Language("Tiếng Anh"));
        AdminHomeActivity.languageDAO.addLanguage(new Language("Tiếng Nhật"));
        AdminHomeActivity.languageDAO.addLanguage(new Language("Tiếng Hàn"));
        AdminHomeActivity.languageDAO.addLanguage(new Language("Tiếng Nga"));
        AdminHomeActivity.languageDAO.addLanguage(new Language("Tiếng Tây Ban Nha"));
        AdminHomeActivity.languageDAO.addLanguage(new Language("Tiếng Đức"));

        AdminHomeActivity.genreDAO.addGenre(new Genre("Kinh dị"));
        AdminHomeActivity.genreDAO.addGenre(new Genre("Hành động"));
        AdminHomeActivity.genreDAO.addGenre(new Genre("Hài"));
        AdminHomeActivity.genreDAO.addGenre(new Genre("Hoạt hình"));
        AdminHomeActivity.genreDAO.addGenre(new Genre("Viễn tưởng"));
        AdminHomeActivity.genreDAO.addGenre(new Genre("Lãng mạn"));
        AdminHomeActivity.genreDAO.addGenre(new Genre("Chiến tranh"));

        AdminHomeActivity.userDAO.addUser(new User("Admin","1234", "admin"));
        AdminHomeActivity.userDAO.addUser(new User("u1","1234", "user"));


    }
}
