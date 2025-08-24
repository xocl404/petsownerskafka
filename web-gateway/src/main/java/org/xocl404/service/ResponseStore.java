package org.xocl404.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.xocl404.OwnerDto;
import org.xocl404.PageDto;
import org.xocl404.PetDto;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class ResponseStore {


    private final BlockingQueue<OwnerDto> ownerQueue = new LinkedBlockingQueue<>();

    private final BlockingQueue<PetDto> petQueue = new LinkedBlockingQueue<>();

    private final BlockingQueue<PageDto<OwnerDto>> ownerPageQueue = new LinkedBlockingQueue<>();

    private final BlockingQueue<PageDto<PetDto>> petPageQueue = new LinkedBlockingQueue<>();

    public void addOwnerResponse(OwnerDto dto) {
        ownerQueue.offer(dto);
    }

    public OwnerDto getOwnerResponse() throws InterruptedException {
        return ownerQueue.poll(2, TimeUnit.SECONDS);
    }

    public void addPetResponse(PetDto dto) {
        petQueue.offer(dto);
    }

    public PetDto getPetResponse() throws InterruptedException {
        return petQueue.poll(2, TimeUnit.SECONDS);
    }

    public void addOwnerPageResponse(PageDto<OwnerDto> pageDto) {
        ownerPageQueue.offer(pageDto);
    }

    public Page<OwnerDto> getOwnerPageResponse() throws InterruptedException {
        return ownerPageQueue.poll(2, TimeUnit.SECONDS).toPage();
    }

    public void addPetPageResponse(PageDto<PetDto> pageDto) {
        petPageQueue.offer(pageDto);
    }

    public Page<PetDto> getPetPageResponse() throws InterruptedException {
        return petPageQueue.poll(2, TimeUnit.SECONDS).toPage();
    }
}
