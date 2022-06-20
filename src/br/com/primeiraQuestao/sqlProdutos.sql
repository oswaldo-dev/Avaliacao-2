CREATE DATABASE produto;

CREATE TABLE produtos (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
descricao VARCHAR(500) NOT NULL,
quantidade INT,
preco DOUBLE);

CREATE TABLE produtos_a_cadastrar (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
descricao VARCHAR(500) NOT NULL,
quantidade INT,
preco DOUBLE);

INSERT INTO `produto`.`produtos_a_cadastrar` (`nome`, `descricao`, `quantidade`, `preco`) VALUES ('Grand Theft Auto V', 'Grand Theft Auto V é um jogo eletrônico de ação-aventura desenvolvido pela Rockstar North e publicado pela Rockstar Games.', '10', '150.00');
INSERT INTO `produto`.`produtos_a_cadastrar` (`nome`, `descricao`, `quantidade`, `preco`) VALUES ('The Witcher 3: Wild Hunt', 'The Witcher 3: Wild Hunt é um jogo eletrônico de RPG de ação em mundo aberto desenvolvido pela CD Projekt RED e lançado no dia 19 de maio de 2015 para as plataformas Microsoft Windows, PlayStation 4, Xbox One e em outubro de 2019 para o Nintendo Switch, sendo o terceiro título da série de jogos The Witcher.', '10', '39.99');
INSERT INTO `produto`.`produtos_a_cadastrar` (`nome`, `descricao`, `quantidade`, `preco`) VALUES ('Minecraft', 'Minecraft é um jogo eletrônico sandbox de sobrevivência criado pelo desenvolvedor sueco Markus \"Notch\" Persson e posteriormente desenvolvido e publicado pela Mojang Studios, cuja propriedade intelectual foi obtida pela Microsoft em 2014.', '10', '7.49');
INSERT INTO `produto`.`produtos_a_cadastrar` (`nome`, `descricao`, `quantidade`, `preco`) VALUES ('God of War', 'God of War é um jogo eletrônico de ação-aventura desenvolvido pela Santa Monica Studio e publicado pela Sony Interactive Entertainment. Foi lançado em 20 de abril de 2018 para PlayStation 4 e em 14 de janeiro de 2022 para Microsoft Windows.', '10', '99.50');
INSERT INTO `produto`.`produtos_a_cadastrar` (`nome`, `descricao`, `quantidade`, `preco`) VALUES ('Batman: Arkham City', 'Batman: Arkham City é um jogo eletrônico de Ação-Aventura e Stealth, baseado na série de quadrinhos Batman da DC Comics. O jogo é distribuído para: PlayStation 3, Xbox 360 e Microsoft Windows. Foi desenvolvido pela Rocksteady Studios e foi publicado pela Warner Bros. Interactive Entertainment e DC Entertainment.', '10', '19.99');
INSERT INTO `produto`.`produtos_a_cadastrar` (`nome`, `descricao`, `quantidade`, `preco`) VALUES ('Doom', 'Doom é um jogo de computador lançado em 1994 pela id Software e um dos títulos que geraram o gênero tiro em primeira pessoa.', '10', '19.99');
INSERT INTO `produto`.`produtos_a_cadastrar` (`nome`, `descricao`, `quantidade`, `preco`) VALUES ('The Last of Us', 'The Last of Us é um jogo eletrônico de ação-aventura e sobrevivência desenvolvido pela Naughty Dog e publicado pela Sony Computer Entertainment. Ele foi lançado exclusivamente para PlayStation 3 em 14 de junho de 2013', '10', '99.50');
INSERT INTO `produto`.`produtos_a_cadastrar` (`nome`, `descricao`, `quantidade`, `preco`) VALUES ('Final Fantasy VII', 'Final Fantasy VII é um jogo eletrônico de RPG desenvolvido e publicado pela SquareSoft. É o sétimo título principal da série Final Fantasy e foi lançado originalmente para PlayStation em 1997 e depois também para Microsoft Windows no ano seguinte.', '10', '11.99');
INSERT INTO `produto`.`produtos_a_cadastrar` (`nome`, `descricao`, `quantidade`, `preco`) VALUES ('Red Dead Redemption 2', 'Red Dead Redemption 2 é um jogo eletrônico de ação-aventura desenvolvido e publicado pela Rockstar Games. É o terceiro título da série Red Dead e uma prequela de Red Dead Redemption, tendo sido lançado em outubro de 2018 para PlayStation 4 e Xbox One e em novembro de 2019 para Microsoft Windows e Google Stadia.', '10', '149.97');
INSERT INTO `produto`.`produtos_a_cadastrar` (`nome`, `descricao`, `quantidade`, `preco`) VALUES ('The Legend of Zelda', 'The Legend of Zelda: Breath of the Wild é um jogo eletrônico de ação-aventura desenvolvido pela Nintendo Entertainment Planning & Development e publicado pela Nintendo. É o décimo nono título da série The Legend of Zelda e foi lançado mundialmente para Wii U e Nintendo Switch em 3 de março de 2017.', '10', '347.70');


