package com.preschool.demo.controller.subscription;

import com.preschool.demo.controller.base.AbstractController;
import com.preschool.demo.controller.base.Converter;
import com.preschool.demo.controller.base.CustomPagedResource;
import com.preschool.demo.controller.base.CustomPagedResourceConverter;
import com.preschool.demo.controller.dto.SubscriptionDto;
import com.preschool.demo.controller.mapper.SubscriptionMapper;
import com.preschool.demo.controller.resource.SubscriptionResource;
import com.preschool.demo.data.entity.subscription.Subscription;
import com.preschool.demo.service.AbstractEntityService;
import com.preschool.demo.service.subscription.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
//@PreAuthorize("hasAnyAuthority('" + AuthorityType.Names.MNG_POST + "')")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/subscription"})
public class SubscriptionController extends AbstractController<SubscriptionDto, Subscription, SubscriptionResource, String> {

    private final SubscriptionMapper mapper;
    private final SubscriptionService service;

    protected AbstractEntityService<Subscription, String> getService() {
        return service;
    }

    @Override
    protected Converter<SubscriptionDto, Subscription, SubscriptionResource> getConverter() {
        return mapper;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        getService().delete(id);
    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping
    public SubscriptionResource save(@RequestBody SubscriptionDto dto) {
        Subscription entity = service.save(getConverter().toEntity(dto));
        return toResource(entity);
    }

    @PutMapping("/{id}")
    public SubscriptionResource put(@PathVariable("id") String id, @RequestBody SubscriptionDto dto) {
        Subscription forSave = getConverter().toEntity(dto);
        Subscription entity = service.put(id, forSave);
        return toResource(entity);
    }

    //@PreAuthorize("isAuthenticated()")
   /* @GetMapping
    public CustomPagedResource<List<SubscriptionResource>> search(@RequestParam(name = "searchText", required = false) String searchText,
                                                                  @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime startDate,
                                                                  @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime endDate,
                                                                  @RequestParam("page") int page,
                                                                  @RequestParam("size") int size,
                                                                  @RequestParam(name = "sort", required = false, defaultValue = "createdDate") String sort,
                                                                  @RequestParam(name = "direction", required = false, defaultValue = "ASC") String direction) {

        Page<SubscriptionResource> map = service
                .search(startDate, endDate, searchText, getPageable(page, size, direction, sort))
                .map(this::toResource);
        return CustomPagedResourceConverter.map(map);
    }*/

/*
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/download-excel", produces = MediaType.ALL_VALUE)
    public ResponseEntity<byte[]> downloadExcel(@RequestParam(name = "searchText", required = false) String searchText, HttpServletResponse response) {
        List<PostFeedback> list = service.search(searchText);
        if (CollectionUtils.isEmpty(list)) {
            throw new BadRequestException(MessageType.RECORD_NOT_FOUND.getKey());
        }
        try {
            return ResponseEntity
                    .ok()
                    .headers(FileHeaderUtil.fileHttpHeaders("post-feedback", ".xls"))
                    .body(IOUtils.toByteArray(service.downloadExcel(response, list)));
        } catch (IOException e) {
            log.error(MessageType.DOWNLOAD_EXCEL_ERROR.getKey(), e);
            throw new BadRequestException(MessageType.EXCEL_CREATING_ERROR.getKey() + e.getMessage());
        }
    }

*/

}
