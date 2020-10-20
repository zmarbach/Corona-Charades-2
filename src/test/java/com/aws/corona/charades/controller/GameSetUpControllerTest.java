//package com.aws.corona.charades.controller;
//
//import com.aws.corona.charades.TeamObjectMother;
//import com.aws.corona.charades.domain.CategoryMap;
//import com.aws.corona.charades.domain.Game;
//import com.aws.corona.charades.domain.Player;
//import com.aws.corona.charades.domain.PlayerForm;
//import com.aws.corona.charades.domain.TeamsViewForm;
//import com.aws.corona.charades.repositories.GameRepository;
//import com.aws.corona.charades.service.GameService;
//import com.aws.corona.charades.service.GameSetUpService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Random;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
////TODO - set up mocks so can verify the methods called on them
//
//class GameSetUpControllerTest {
//    private static final String HOME_URL = "/home";
//    private static final String HOME_VIEW_NAME = "home";
//    private static final String REDIRECT = "redirect:/";
//    private static final String TEAMS_URL = "/teams";
//    private static final String TEAMS_VIEW_NAME = "teams";
//    private static final String PLAYER_NAMES_TEAM_ONE_URL = "/player-names-team-one";
//    private static final String PLAYER_NAMES_TEAM_ONE_VIEW_NAME = "player-names-team-one";
//    private static final String PLAYER_NAMES_TEAM_TWO_URL = "/player-names-team-two";
//    private static final String PLAYER_NAMES_TEAM_TWO_VIEW_NAME = "player-names-team-two";
//    private static final String GAME_PLAY_URL = "/game-play";
//
//    private UUID hardcodedUUID = new UUID(1,1);
//    private Random random = new Random();
//    private CategoryMap categoryMap = new CategoryMap(new HashMap<>());
//    private GameRepository gameRepository = new GameRepository(new HashMap<>(), new UuidGeneratorTestImpl());
//    private GameService gameService = new GameService(gameRepository);
//    private GameSetUpService gameSetUpService = new GameSetUpService(categoryMap, gameService);
//    private GameSetUpController testController = new GameSetUpController(gameSetUpService, gameService);
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/jsp/");
//        viewResolver.setSuffix(".jsp");
//
//        mockMvc = MockMvcBuilders.standaloneSetup(testController)
//                                 .setViewResolvers(viewResolver)
//                                 .build();
//    }
//
//    @Test
//    void displayHomePage() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get(HOME_URL))
//                .andExpect(status().isOk())
//                .andExpect(view().name(HOME_VIEW_NAME));
//    }
//
//    @Test
//    void createNewGame() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post(HOME_URL))
//                .andExpect(view().name(REDIRECT + hardcodedUUID + TEAMS_URL));
//    }
//
//    @Test
//    void displayTeamsForm() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/" + hardcodedUUID + TEAMS_URL))
//                .andExpect(model().attributeExists("teamsViewForm"))
//                .andExpect(model().attributeExists("categoryNames"))
//                .andExpect(view().name(TEAMS_VIEW_NAME));
//    }
//
//    @Test
//    void teamsRedirect() throws Exception {
//        Game game = buildTestGame();
//        mockMvc.perform(MockMvcRequestBuilders.post(TEAMS_URL)
//                .flashAttr("teamsViewForm", new TeamsViewForm(hardcodedUUID,1, 1, 2, "General")))
//                .andExpect(view().name(REDIRECT + hardcodedUUID + PLAYER_NAMES_TEAM_ONE_URL));
//    }
//
//    @Test
//    void displayPlayerNamesForTeamOne() throws Exception {
//        gameService.createNewGame();
//        mockMvc.perform(MockMvcRequestBuilders.get("/" + hardcodedUUID +  PLAYER_NAMES_TEAM_ONE_URL))
//                .andExpect(model().attributeExists("playerForm"))
//                .andExpect(view().name(PLAYER_NAMES_TEAM_ONE_VIEW_NAME));
//    }
//
//    @Test
//    void updateTeamOnePlayerNames() throws Exception {
//        Game game = buildTestGame();
//        mockMvc.perform(MockMvcRequestBuilders.post(PLAYER_NAMES_TEAM_ONE_URL)
//                .flashAttr("playerForm", new PlayerForm(game.getTeamOne().getPlayers(), game.getUuid())))
//                .andExpect(view().name(REDIRECT + hardcodedUUID + PLAYER_NAMES_TEAM_TWO_URL));
//    }
//
//    @Test
//    void displayPlayerNamesForTeamTwo() throws Exception {
//        gameService.createNewGame();
//        mockMvc.perform(MockMvcRequestBuilders.get("/" + hardcodedUUID +  PLAYER_NAMES_TEAM_TWO_URL))
//                .andExpect(model().attributeExists("playerForm"))
//                .andExpect(view().name(PLAYER_NAMES_TEAM_TWO_VIEW_NAME));
//    }
//
//    @Test
//    void updateTeamTwoPlayerNames() throws Exception {
//        Game game = buildTestGame();
//        mockMvc.perform(MockMvcRequestBuilders.post(PLAYER_NAMES_TEAM_TWO_URL)
//                .flashAttr("playerForm", new PlayerForm(game.getTeamOne().getPlayers(), game.getUuid())))
//                .andExpect(view().name(REDIRECT + hardcodedUUID + GAME_PLAY_URL));
//
//        assertEquals(game.getCurrentPlayer(), game.getTeamOne().getPlayers().get(0));
//    }
//
//    private Game buildTestGame(){
//        Game game = gameService.createNewGame();
//        game.setTeamOne(TeamObjectMother.createTeamOne());
//        game.getTeamOne().getPlayers().add(new Player("Zach", game.getTeamOne()));
//        game.setTeamTwo(TeamObjectMother.createTeamTwo());
//        game.getTeamTwo().getPlayers().add(new Player("Kelli", game.getTeamTwo()));
//        game.setActiveWords(Arrays.asList("foo", "bar"));
//        game.setGuessedWords(new ArrayList<>());
//        game.setCurrentWord("foo");
//        game.setCurrentPlayer(game.getTeamOne().getPlayers().get(0));
//        game.setNewTurn(true);
//        return game;
//    }
//}