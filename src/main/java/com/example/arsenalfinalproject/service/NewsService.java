package com.example.arsenalfinalproject.service;

import com.example.arsenalfinalproject.model.binding.NewsAddBindingModel;
import com.example.arsenalfinalproject.model.service.NewsAddServiceModel;
import com.example.arsenalfinalproject.model.service.NewsUpdateServiceModel;
import com.example.arsenalfinalproject.model.view.NewsDetailsView;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface NewsService {


//    List<NewsDetailsView> findAllNews();

    void initializeNews() throws IOException;

    NewsDetailsView findById(Long id , String currentUser);

    boolean isExistId(Long id);


    NewsAddServiceModel addNews(NewsAddBindingModel newsAddBindingModel, String userIdentifier) throws IOException;

    void deleteProduct(Long id, String publicId);

    boolean isOwner(String userName , Long id);


    void updateOffer(NewsUpdateServiceModel newsUpdateServiceModel);

    Page<NewsDetailsView> findPaginated(int pageNo , int pageSize);
}
