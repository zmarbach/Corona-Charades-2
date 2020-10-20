//package com.aws.corona.charades.controller;
//
//import com.aws.corona.charades.domain.CategoryMap;
//import com.aws.corona.charades.domain.Game;
//import com.aws.corona.charades.repositories.GameRepository;
//import com.aws.corona.charades.service.GamePlayService;
//import com.aws.corona.charades.service.GameService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.ArgumentMatchers.anyObject;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.net.URI;
//import java.util.HashMap;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//class GamePlayControllerTest {
//
//    //TODO - Use Mockito to Mock the Game PLAY Service because
//    // MUST be able to mock the returned values from those methods and verify what was called
//    // AND how many times
//
//    private static final UUID HARDCODED_UUID = new UUID(1,1);
//    private static final String UUID_GAMEPLAY_URL= "/" + HARDCODED_UUID + "/game-play";
//    private static final String START_TURN_URL = "/start-turn";
//    private static final String CORRECT_URL = "/correct";
//    private static final String SKIP_URL = "/skip";
//    private static final String NEXT_PLAYER_URL = "/next-player";
//    private static final String NEXT_ROUND_URL = "/next-round";
//    private static final String END_GAME_URL = "/end-game";
//    private static final String GAMEPLAY_REDIRECT_URL = "redirect:/" + HARDCODED_UUID + "/game-play";
//    private static final String HOME_URL = "/home";
//
//    @Mock
//    GamePlayService gamePlayService;
//    private GameRepository gameRepository = new GameRepository(new HashMap<>(), new UuidGeneratorTestImpl());
//    private GameService gameService = new GameService(gameRepository);
//    private GamePlayController testController;
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        testController = new GamePlayController(gamePlayService, gameService);
//
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/WEB-INF/jsp/");
//        viewResolver.setSuffix(".jsp");
//
//        mockMvc = MockMvcBuilders.standaloneSetup(testController)
//                .setViewResolvers(viewResolver)
//                .build();
//
//        gameService.createNewGame();
//    }
//
//    @Test
//    void updateGamePlayPage() {
//    }
//
//    @Test
//    void startTurn() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post(START_TURN_URL).param("gameUUID", HARDCODED_UUID.toString()))
//                .andExpect(status().is(200))
//                .andExpect(view().name(GAMEPLAY_REDIRECT_URL));
//
//        verify(gamePlayService, times(1)).handleStartTurn(any(Game.class));
//    }
//
//    @Test
//    void handleCorrectGuess() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post(CORRECT_URL).param("gameUUID", HARDCODED_UUID.toString()))
//                .andExpect(status().is(200))
//                .andExpect(view().name(GAMEPLAY_REDIRECT_URL));
//
//        verify(gamePlayService, times(1)).handleCorrect(any(Game.class));
//    }
//
//    @Test
//    void handleSkip() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post(SKIP_URL).param("gameUUID", HARDCODED_UUID.toString()))
//                .andExpect(status().is(200))
//                .andExpect(view().name(GAMEPLAY_REDIRECT_URL));
//
//        verify(gamePlayService, times(1)).handleSkip(any(Game.class));
//    }
//
//    @Test
//    void nextPlayer() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post(NEXT_PLAYER_URL).param("gameUUID", HARDCODED_UUID.toString()))
//                .andExpect(status().is(200))
//                .andExpect(view().name(GAMEPLAY_REDIRECT_URL));
//
//        verify(gamePlayService, times(1)).handleNextPlayer(any(Game.class));
//    }
//
//    @Test
//    void nextRound() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post(NEXT_ROUND_URL).param("gameUUID", HARDCODED_UUID.toString()))
//                .andExpect(status().is(200))
//                .andExpect(view().name(GAMEPLAY_REDIRECT_URL));
//
//        verify(gamePlayService, times(1)).handleNextRound(any(Game.class));
//    }
//
//    @Test
//    void endGame() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post(END_GAME_URL).param("gameUUID", HARDCODED_UUID.toString()))
//                .andExpect(status().is(200))
//                .andExpect(view().name(HOME_URL));
//
//        verify(gamePlayService, times(1)).handleEndGame(any(Game.class));
//    }
//}